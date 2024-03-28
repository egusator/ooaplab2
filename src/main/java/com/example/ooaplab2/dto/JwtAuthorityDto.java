package com.example.ooaplab2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record JwtAuthorityDto(

        @Schema(description = "JWT токен", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
        String jwt,

        @Schema(description = "ID пользователя", example = "123")
        long id,

        @Schema(description = "Список ролей пользователя")
        List<String> authorities
) {}

