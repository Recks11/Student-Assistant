package com.emmanuelirem.studentassistant.services;

import com.emmanuelirem.studentassistant.models.security.Users;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsersService {
    Mono<Users> findByUsername(String username);
    Mono<Users> findById(String id);
    Mono<Users> save(Users users);
    Flux<Users> findAll();
}
