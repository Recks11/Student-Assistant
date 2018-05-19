package com.emmanuelirem.studentassistant.repository;

import com.emmanuelirem.studentassistant.models.security.Users;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UsersDetailsRepository extends ReactiveMongoRepository<Users, String> {
    Mono<Users> findByUsername(String username);
}
