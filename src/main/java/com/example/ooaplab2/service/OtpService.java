package com.example.ooaplab2.service;

import com.example.ooaplab2.entity.Otp;
import com.example.ooaplab2.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpGenerator otpGenerator;

    private final EmailService emailService;

    private final OtpRepository emailOtpRepository;


    public void sendOtpCode(String email, String title, String message) {
        String code = otpGenerator.generateOtpCode();
        Otp otp = emailOtpRepository.getOtpCodeByEmail(email);
        if (otp == null)
            otp = new Otp(email, null);
        otp.setOtpValue(Integer.parseInt(code));
        emailOtpRepository.save(otp);
        emailService.sendMessage(email, title, message + code);
    }

    public void validateCode(String otpCodeValue, String email) {
        Otp otp = emailOtpRepository.getOtpCodeByEmail(email);
        if (otp == null || !String.valueOf(otp.getOtpValue()).equals(otpCodeValue)) {
            throw new RuntimeException("Incorrect email or value of otp code.");
        }
    }
}
