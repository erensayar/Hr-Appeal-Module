package com.erensayar.HrWebApplicationApi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Received object if save or update to db as directly, this situation creates a security vulnerability.
 * This class created for made to avoid security vulnerabilities.
 */
@Getter
@Setter
@Builder
public class AdminDto {
    private Integer id;
    private String name;
    private String surname;
    private String personalMail;
    private String workMail;
    private String password;
}
