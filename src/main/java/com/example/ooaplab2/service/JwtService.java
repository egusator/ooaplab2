package com.example.ooaplab2.service;

import com.example.ooaplab2.config.JwtProperty;
import com.example.ooaplab2.dto.JwtAuthorityDto;
import com.example.ooaplab2.entity.Authority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.ClockProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JwtService {
    private final JwtProperty jwtProperty;
    private final ClockProvider clockProvider;
    public JwtAuthorityDto generateToken(long userId, List<Authority> userAuthorities) {
        Map<String, Object> claims = new HashMap<>();
        claims
                .put("authorities", userAuthorities
                        .stream()
                        .map(Authority::getAuthorityName)
                        .collect(Collectors.toList()));
        String token = createToken(claims, String.valueOf(userId));
        List<String> authorities = userAuthorities.stream().map(Authority::getAuthorityName).toList();
        return new JwtAuthorityDto(token, userId, authorities);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(clockProvider.getClock().millis()))
                .expiration(
                        Date.from(
                                new Date(clockProvider.getClock().millis())
                                        .toInstant()
                                        .plus(Duration.ofHours(2))))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode("dnlwYzNoMWNheDFocmNmY3NkZ3FzNDllOGtuOTF0b21seTRzOXI2MndwNzB6anpmemw2a2s4cjVha253NmVvYw"));
    }

    public Claims extractAllClaims(String token) {

            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    public List<String> extractAuthorities(String token) {
        return extractClaim(token, claims -> claims.get("authorities", List.class));
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public void validate(String token) {
        try {
            if (Jwts.parser()
                    .verifyWith(getSigningKey()) // Выбросит JwtException,
                    .build()                     // если не сможет верифицировать
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration()
                    .before(new Date(clockProvider.getClock().millis()))) {
                throw new RuntimeException("The token expired!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid token!");
        }
    }
}
