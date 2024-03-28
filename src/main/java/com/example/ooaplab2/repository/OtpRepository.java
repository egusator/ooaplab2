package com.example.ooaplab2.repository;

import com.example.ooaplab2.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, String> {
    Otp getOtpCodeByEmail(String email);
}
