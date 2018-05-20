package com.emmanuelirem.studentassistant.config;

import com.emmanuelirem.studentassistant.services.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .authorizeExchange()
                .pathMatchers("/auth/**")
                .permitAll()
                .pathMatchers("/api", "/logout", "/user")
                .authenticated()
                .pathMatchers("/student/{id}/**")
                .access(this::currentUserMatchesPath)
                .pathMatchers("/lecturer/{id}/**")
                .access(this::currentUserMatchesPath)
                .anyExchange()
                .authenticated()
                .and()
                .csrf().disable()
                .httpBasic()
                .securityContextRepository(new WebSessionServerSecurityContextRepository())
                .and()
                .formLogin().disable()
                .logout().disable()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private Mono<AuthorizationDecision> currentUserMatchesPath(Mono<Authentication> authentication, AuthorizationContext context) {
        return authentication
                .map(a -> context.getVariables().get("id").equals(a.getName()))
                .map(AuthorizationDecision::new);
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(UsersService users) {
        return username -> users.findByUsername(username).cast(UserDetails.class);
    }
}