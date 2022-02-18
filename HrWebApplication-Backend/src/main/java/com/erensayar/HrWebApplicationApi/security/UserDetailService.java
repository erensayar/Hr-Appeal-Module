package com.erensayar.HrWebApplicationApi.security;

import com.erensayar.HrWebApplicationApi.model.entity.Admin;
import com.erensayar.HrWebApplicationApi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
