package com.erensayar.HrAppealModuleApi.security;

import com.erensayar.HrAppealModuleApi.error.exception.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtTokenManager jwtTokenManager;
    private final AuthenticationManager authenticationManager;

    public String login(LoginRequest loginRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            return jwtTokenManager.generateToken(loginRequest.getUsername());

        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

}
