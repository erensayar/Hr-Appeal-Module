package com.erensayar.HrAppealModuleApi.model.dto;

import com.erensayar.HrAppealModuleApi.security.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private String username;
    private String surname;
    private String personalMail;
    private String workMail;
    private String password;
    List<UserRole> userRoles;
}
