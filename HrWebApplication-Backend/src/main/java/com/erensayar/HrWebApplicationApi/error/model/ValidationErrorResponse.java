package com.erensayar.HrWebApplicationApi.error.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ValidationErrorResponse {
    private String errorType;
    private String errorCode;
    private Map<String, String> errorMessage;
}
