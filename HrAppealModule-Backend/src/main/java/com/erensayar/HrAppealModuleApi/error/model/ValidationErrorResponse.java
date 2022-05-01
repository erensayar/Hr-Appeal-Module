package com.erensayar.HrAppealModuleApi.error.model;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ValidationErrorResponse {

  private String errorType;
  private String errorCode;
  private Map<String, String> errorMessage;

}
