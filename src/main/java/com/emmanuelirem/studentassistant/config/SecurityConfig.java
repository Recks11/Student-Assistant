package com.emmanuelirem.studentassistant.config;

import com.emmanuelirem.studentassistant.services.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .authorizeExchange()
                .pathMatchers("/auth/")
                .permitAll()
                .pathMatchers("/api", "/logout")
                .authenticated()
                .pathMatchers("/student")
                .hasAuthority("STUDENT")
                .pathMatchers("/lecturer")
                .hasAuthority("LECTURER")
                .anyExchange()
                .authenticated()
                .and()
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public ReactiveUserDetailsService userDetailsService(UsersService users) {
        return username -> users.findByUsername(username).cast(UserDetails.class);
    }
}