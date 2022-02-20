package com.erensayar.HrAppealModuleApi.model.entity;

import lombok.*;

import java.util.Map;

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

    private Map<String, Object> props;

}
