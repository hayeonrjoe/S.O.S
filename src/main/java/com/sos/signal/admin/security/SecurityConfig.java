package com.sos.signal.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/adminlogin", "/adminregister").permitAll()
                .anyRequest().authenticated();

        http
                .formLogin()
                .loginPage("/adminlogin")
                .loginProcessingUrl("/auth")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/");

        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        return http.build();
    }
}