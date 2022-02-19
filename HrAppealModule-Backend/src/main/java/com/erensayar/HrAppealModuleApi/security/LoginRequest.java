package com.erensayar.HrAppealModuleApi.security;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;
    private String password;
}
