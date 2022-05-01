package com.erensayar.HrAppealModuleApi.service.impl;

import com.erensayar.HrAppealModuleApi.model.entity.Mail;
import com.erensayar.HrAppealModuleApi.model.enums.MailTemplateName;
import com.erensayar.HrAppealModuleApi.service.MailService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

  // TODO: !!! There are so many repetitions !!!!!!!
  //  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  //  !!!!!!!!!! WILL BE REFACTORING !!!!!!!!!!!!!!!
  //  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

  private final JavaMailSender javaMailSender;
  private final SpringTemplateEngine templateEngine;

  @Override
  public void sendMail(Mail mail) {
    SimpleMailMessage m = new SimpleMailMessage();
    m.setTo(mail.getTo());
      if (mail.getCc() != null) {
          m.setCc(mail.getCc());
      }
      if (mail.getBcc() != null) {
          m.setBcc(mail.getBcc());
      }
    m.setSubject(mail.getSubject());
    m.setText(mail.getBody());
    javaMailSender.send(m);
  }

  @Override
  public void sendMailWithAttachment(Mail mail, MultipartFile attachment) {
    try {
      MimeMessage msg = javaMailSender.createMimeMessage();
      //MimeMessageHelper helper = new MimeMessageHelper(m, true); // true -> multipart message
      MimeMessageHelper helper = new MimeMessageHelper(
          msg,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
          StandardCharsets.UTF_8.name());

      // Create Mail
      helper.setTo(mail.getTo());
        if (mail.getCc() != null) {
            helper.setCc(mail.getCc());
        }
        if (mail.getBcc() != null) {
            helper.setBcc(mail.getBcc());
        }
      helper.setSubject(mail.getSubject());
      helper.setText(mail.getBody());

      // Attachment
      byte[] byteArr = attachment.getBytes();
      InputStream inputStream = new ByteArrayInputStream(byteArr);
      ByteArrayDataSource attachmentAsByteArrayDataSource = new ByteArrayDataSource(inputStream,
          "application/octet-stream");
      helper.addAttachment(attachment.getOriginalFilename(), attachmentAsByteArrayDataSource);

      javaMailSender.send(msg);
    } catch (MessagingException | IOException e) {
      log.error(e.getMessage());
    }
  }

  @Override
  public void sendMailAsTemplate(Mail mail, Integer mailTemplateNo) {
    try {
      MimeMessage msg = javaMailSender.createMimeMessage();
      //MimeMessageHelper helper = new MimeMessageHelper(m, true); // true -> multipart message
      MimeMessageHelper helper = new MimeMessageHelper(
          msg,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
          StandardCharsets.UTF_8.name());

      // Create Mail
      helper.setTo(mail.getTo());
        if (mail.getCc() != null) {
            helper.setCc(mail.getCc());
        }
        if (mail.getBcc() != null) {
            helper.setBcc(mail.getBcc());
        }
      helper.setSubject(mail.getSubject());

      // Template
      Context context = new Context();
      context.setVariables(mail.getProps());
      String htmlFileName = MailTemplateName.values()[mailTemplateNo].toString();
      String html = templateEngine.process(htmlFileName, context);
      helper.setText(html, true); // Set mail body as template

      javaMailSender.send(msg);
    } catch (MessagingException e) {
      log.error(e.getMessage());
    }
  }

  @Override
  public void sendMailAsTemplateWithAttachment(Mail mail, Integer mailTemplateNo,
      MultipartFile attachment) {
    try {
      MimeMessage msg = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(
          msg,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
          StandardCharsets.UTF_8.name());

      // Create Mail
      helper.setTo(mail.getTo());
        if (mail.getCc() != null) {
            helper.setCc(mail.getCc());
        }
        if (mail.getBcc() != null) {
            helper.setBcc(mail.getBcc());
        }
      helper.setSubject(mail.getSubject());

      // Template
      Context context = new Context();
      context.setVariables(mail.getProps());
      String htmlFileName = MailTemplateName.values()[mailTemplateNo].toString();
      String html = templateEngine.process(htmlFileName, context);
      helper.setText(html, true); // Set mail body as template

      // Attachment
      byte[] byteArr = attachment.getBytes();
      InputStream inputStream = new ByteArrayInputStream(byteArr);
      ByteArrayDataSource attachmentAsByteArrayDataSource = new ByteArrayDataSource(inputStream,
          "application/octet-stream");
      helper.addAttachment(attachment.getOriginalFilename(), attachmentAsByteArrayDataSource);

      javaMailSender.send(msg);
    } catch (MessagingException | IOException e) {
      log.error(e.getMessage());
    }
  }

}
