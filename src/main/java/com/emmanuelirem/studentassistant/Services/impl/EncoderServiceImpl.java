package com.emmanuelirem.studentassistant.Services.impl;

import com.emmanuelirem.studentassistant.Services.EncoderService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncoderServiceImpl implements EncoderService{

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}