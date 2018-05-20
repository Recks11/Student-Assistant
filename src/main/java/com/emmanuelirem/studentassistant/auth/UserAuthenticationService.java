package com.emmanuelirem.studentassistant.auth;

import com.emmanuelirem.studentassistant.models.security.Users;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import reactor.core.publisher.Mono;

public interface UserAuthenticationService extends ReactiveUserDetailsService {

    /**
     * Logs in with the given {@code username} and {@code password}.
     *
     * @param username
     * @param password
     * @return an {@link Mono} of a user when login succeeds
     */
    Mono<String> login(String username, String password);

    /**
     * Finds a user by its dao-key.
     *
     * @param token user dao key
     * @return
     */
    Mono<Users> findByToken(String token);

    /**
     * Logs out the given input {@code user}.
     *
     * @param user the user to logout
     */
    Mono<Void> logout(Users user);
}
