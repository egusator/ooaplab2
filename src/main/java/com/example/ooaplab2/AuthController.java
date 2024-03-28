package com.example.ooaplab2;

import com.example.ooaplab2.dto.JwtAuthorityDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Аунтентификация")
@Validated
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthFacade authFacade;

    @Operation(summary = "Отправка OTP кода на указанный email")
    @PostMapping("/send-code")
    public void sendOtpCode(@RequestParam String email) {
        authFacade.sendOtpToEmail(email);
    }

    @Operation(summary = "Авторизация (генерация JWT) по отправке email и OTP")
    @GetMapping("/auth")
    public JwtAuthorityDto authWithEmailAndOtpCode(

            @RequestParam("email")

            @Parameter(description = "Email пользователя")
            String email,
            @RequestParam("otp")
            @Parameter(description = "Значение otp-кода, полученное на почту")
            String otpCodeValue
    ) {
        return authFacade.getJwt(email, otpCodeValue);
    }

}
