package com.erensayar.HrAppealModuleApi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenManager {

  Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  @Value("${security.jwt.expire-time}")
  private long validity;

  public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + validity))
        .signWith(key)
        .compact();
  }

  public boolean tokenValidate(String token) {
    return getUsernameToken(token) != null && isExpired(token);
  }

  public String getUsernameToken(String token) {
    Claims claims = getClaims(token);
    return claims.getSubject();
  }

  public boolean isExpired(String token) {
    Claims claims = getClaims(token);
    return claims.getExpiration().after(new Date(System.currentTimeMillis()));
  }

  private Claims getClaims(String token) {
    return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
  }

}
