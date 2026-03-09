package com.makichanov.core.util.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public final class JwtTokenUtils {

    private static final String JWT_SECRET = "hhg9z5fF13Y5UYmwy6Dh61p2eQGAJVNYy4Ub2Em4c6csuDJ2eVkHysbcsRs1";

    private static final SecretKey KEY = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

    private static final int TOKEN_EXPIRATION_DAYS = 5;

    private JwtTokenUtils() {}

    public static String generateToken(String username) {
        Date expirationDate = Date.from(
            LocalDate.now()
                .plusDays(TOKEN_EXPIRATION_DAYS)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
        );

        return Jwts.builder()
            .subject(username)
            .expiration(expirationDate)
            .signWith(KEY)
            .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            log.error("Attempting to parse invalid JWT", e);
        } catch (Exception e) {
            log.error("Exception has been thrown while validating JWT", e);
        }
        return false;
    }

    public static String getLoginFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
            .verifyWith(KEY)
            .build()
            .parseSignedClaims(token);

        return claimsJws.getPayload().getSubject();
    }

    public static String extractJwtFromHeader(String authorizationHeader) {
        return StringUtils.hasText(authorizationHeader)
            ? authorizationHeader.replaceFirst("Bearer ", "")
            : null;
    }
}
