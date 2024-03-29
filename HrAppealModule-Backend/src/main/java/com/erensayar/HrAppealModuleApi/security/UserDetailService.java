package com.erensayar.HrAppealModuleApi.security;

import com.erensayar.HrAppealModuleApi.model.entity.Admin;
import com.erensayar.HrAppealModuleApi.service.AdminService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

  private final AdminService adminService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Admin> optAdmin = adminService.getOptAdminByUsername(username);
    if (optAdmin.isPresent()) {
      return new User(
          optAdmin.get().getUsername(),
          optAdmin.get().getPassword(),
          optAdmin.get().getUserRoles());
    }
    throw new UsernameNotFoundException(username);
  }
}
