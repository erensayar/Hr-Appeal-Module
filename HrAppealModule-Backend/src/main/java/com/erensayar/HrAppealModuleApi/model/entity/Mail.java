package com.erensayar.HrAppealModuleApi.model.entity;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
