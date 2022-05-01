package com.erensayar.HrAppealModuleApi.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum UserRole implements GrantedAuthority {
  ROLE_ADMIN(0),
  ROLE_CLIENT(1);

  private final Integer id;

  public String getAuthority() {
    return name();
  }

}
