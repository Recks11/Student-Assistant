package com.emmanuelirem.studentassistant.services.data.impl;

import com.emmanuelirem.studentassistant.services.data.EncoderService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncoderServiceImpl implements EncoderService{

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}