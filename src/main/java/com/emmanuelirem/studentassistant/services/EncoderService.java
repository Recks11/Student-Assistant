package com.emmanuelirem.studentassistant.services;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface EncoderService {
//TODO make reactive?
    PasswordEncoder passwordEncoder();
}
