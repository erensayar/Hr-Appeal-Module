package com.erensayar.HrAppealModuleApi.controller;

import com.erensayar.HrAppealModuleApi.model.entity.Mail;
import com.erensayar.HrAppealModuleApi.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/mails")
@RequiredArgsConstructor
@RestController
public class MailController {

    private final MailService mailService;

    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> send(@RequestBody Mail mail) {
        mailService.sendMail(mail);
        return ResponseEntity.ok("Mail Sent");
    }

    @PostMapping(value = "/send-with-attachment",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> sendMailWithAttachment(@RequestPart("mail") Mail mail,
                                             @RequestPart("attachment") MultipartFile attachment) {
        mailService.sendMailWithAttachment(mail, attachment);
        return ResponseEntity.ok("Mail Sent");
    }

    @PostMapping(value = "/send/{mailTemplateNo}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> sendMailAsTemplate(@RequestBody Mail mail,
                                         @PathVariable Integer mailTemplateNo) {
        mailService.sendMailAsTemplate(mail, mailTemplateNo);
        return ResponseEntity.ok("Mail Sent");
    }

    @PostMapping(value = "/send-with-attachment/{mailTemplateNo}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> sendMailAsTemplateWithAttachment(@RequestPart("mail") Mail mail,
                                                       @RequestPart("attachment") MultipartFile attachment,
                                                       @PathVariable Integer mailTemplateNo) {
        mailService.sendMailAsTemplateWithAttachment(mail, mailTemplateNo, attachment);
        return ResponseEntity.ok("Mail Sent");
    }

}
