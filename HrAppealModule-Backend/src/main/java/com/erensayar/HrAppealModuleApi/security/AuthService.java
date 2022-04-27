package com.erensayar.HrAppealModuleApi.security;

import com.erensayar.HrAppealModuleApi.error.exception.InternalServerErrorException;
import com.erensayar.HrAppealModuleApi.model.dto.response_dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final JwtTokenManager jwtTokenManager;
  private final AuthenticationManager authenticationManager;

  public TokenDto login(LoginRequest loginRequest) {

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequest.getUsername(),
              loginRequest.getPassword()));

      String token = jwtTokenManager.generateToken(loginRequest.getUsername());

      return TokenDto.builder()
          .token(token)
          .build();
    } catch (Exception e) {
      throw new InternalServerErrorException(e.getMessage());
    }
  }

}
