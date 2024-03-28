package com.example.ooaplab2;

import com.example.ooaplab2.dto.JwtAuthorityDto;
import com.example.ooaplab2.entity.Authority;
import com.example.ooaplab2.service.AuthorityService;
import com.example.ooaplab2.service.JwtService;
import com.example.ooaplab2.service.OtpService;
import com.example.ooaplab2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthFacade {

    private final JwtService jwtService;

    private final UserService userService;

    private final OtpService otpService;

    private final AuthorityService authorityService;

    public void sendOtpToEmail(String email) {
        otpService.sendOtpCode(email, "Код", "Ваш код: ");
    }

    public JwtAuthorityDto getJwt(String email, String otpValue) {
        otpService.validateCode(otpValue, email);
        Long userId = userService.getUserIdByEmail(email);
        List<Authority> authorityList = authorityService.getAuthoritiesByEmail(email);
        return jwtService.generateToken(userId, authorityList);
    }

}
