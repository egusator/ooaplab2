package com.example.ooaplab2.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor

public class OtpGenerator {

    private final SecureRandom secureRandom;

    public String generateOtpCode() {
        return String.format("%06d", secureRandom.nextInt(1000000));
    }

}
