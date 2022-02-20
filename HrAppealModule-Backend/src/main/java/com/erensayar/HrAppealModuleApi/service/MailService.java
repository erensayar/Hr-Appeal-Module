package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.model.entity.Mail;
import org.springframework.web.multipart.MultipartFile;

public interface MailService {

    void sendMail(Mail mail);

    void sendMailWithAttachment(Mail mail, MultipartFile attachment);

    void sendMailAsTemplate(Mail mail, Integer mailTemplateNo);

    void sendMailAsTemplateWithAttachment(Mail mail, Integer mailTemplateNo, MultipartFile attachment);

}
