package com.erensayar.HrWebApplicationApi.security;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;
    private String password;
}
