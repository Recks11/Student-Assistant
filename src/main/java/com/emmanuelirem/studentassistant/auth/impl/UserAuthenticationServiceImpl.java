package com.emmanuelirem.studentassistant.auth.impl;

import com.emmanuelirem.studentassistant.auth.UserAuthenticationService;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

//@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private Map<String, Users> allUsers = new HashMap<>();

    private final UsersService usersService;

    @Autowired
    public UserAuthenticationServiceImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public Mono<String> login(String username, String password) {
        return usersService.findByUsername(username).map(users -> {
            final String token = users.getId();
            allUsers.put(token, users);
            return token;
        }).doOnError(throwable -> {throw new IllegalArgumentException();});
    }

    @Override
    public Mono<Users> findByToken(String token) {
        return Mono.just(allUsers.get(token));
    }

    @Override
    public Mono<Void> logout(Users user) {
        return Mono.just(allUsers.remove(user.getId())).then();
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return usersService.findByUsername(username).map(
                user -> allUsers.get(user.getId()));
    }
}
