package com.emmanuelirem.studentassistant.Services;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface EncoderService {

    PasswordEncoder passwordEncoder();
}
