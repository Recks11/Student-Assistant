package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.repository.UsersDetailsRepository;
import com.emmanuelirem.studentassistant.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersDetailsRepository usersDetailsRepository;

    @Autowired
    public UsersServiceImpl(UsersDetailsRepository usersDetailsRepository) {
        this.usersDetailsRepository = usersDetailsRepository;
    }

    @Override
    public Mono<Users> findByUsername(String username) {
        return usersDetailsRepository.findByUsername(username).doOnError(throwable -> {
            throw new UsernameNotFoundException("Cannot find user with name=" + username);
        });
    }

    @Override
    public Mono<Users> findById(String id) {
        return usersDetailsRepository.findById(id).doOnError(throwable -> {
            throw new UsernameNotFoundException("Cannot find user");
        });
    }

    @Override
    public Mono<Users> save(Users users) {
        return usersDetailsRepository.save(users);
    }

    @Override
    public Flux<Users> findAll() {
        return usersDetailsRepository.findAll();
    }
}
