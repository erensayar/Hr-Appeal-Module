package com.erensayar.HrAppealModuleApi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(authService.login(loginRequest));
  }

  @GetMapping("/try-token")
  public ResponseEntity<String> getMessage() {
    return ResponseEntity.ok("Hello JWT");
  }

}
