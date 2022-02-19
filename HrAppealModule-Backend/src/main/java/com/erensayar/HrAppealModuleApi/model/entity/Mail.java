package com.erensayar.HrAppealModuleApi.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail {

    private String to;

    private String cc;

    private String bcc;

    private String subject;

    private String body;

    //private List<Object> attachments;

}
