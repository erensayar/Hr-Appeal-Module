package com.erensayar.HrAppealModuleApi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  // INJECTION
  //<==============================================================================================>
  private final JwtTokenFilter jwtTokenFilter;

  private UserDetailService userDetailService;

  @Autowired
  public void setUserDetailService(UserDetailService userDetailService) {
    this.userDetailService = userDetailService;
  }

  @Autowired
  public void configurePasswordEncoder(AuthenticationManagerBuilder builder) throws Exception {
    builder.userDetailsService(userDetailService).passwordEncoder(getBCryptPasswordEncoder());
  }

  // CONFIG
  //<==============================================================================================>
  @Override
  @Profile("prod")
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.authorizeRequests()
        .antMatchers("/auth/**").permitAll()
        .antMatchers(HttpMethod.POST, "/api/v1/applicants").permitAll()
        .antMatchers(HttpMethod.POST, "/api/v1/files/upload").permitAll()
        .antMatchers("/api/v1/jobs").permitAll()
        .antMatchers("/api/v1/jobs/detail").permitAll()
        .antMatchers("/api-docs/**").permitAll() // For development
        .antMatchers("/swagger-ui/**").permitAll() // For development
        .antMatchers("/h2-console/**/**").permitAll() // For development
        .anyRequest().authenticated();
    http.headers().frameOptions().disable(); // For development (H2 DB can be Visible From Browser)
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }

  // BEANS
  //<==============================================================================================>
  @Bean
  public BCryptPasswordEncoder getBCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager getAuthenticationManager() throws Exception {
    return super.authenticationManagerBean();
  }

}
