package com.makichanov.core.util.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Slf4j
public final class JwtTokenUtils {

    private static final String JWT_SECRET = "hhg9z5fF13Y5UYmwy6Dh61p2eQGAJVNYy4Ub2Em4c6csuDJ2eVkHysbcsRs1";

    private static final int TOKEN_EXPIRATION_DAYS = 5;

    private JwtTokenUtils() {}

    public static String generateToken(String username, String password) {
        Date expirationDate = Date.from(
                LocalDate.now()
                        .plusDays(TOKEN_EXPIRATION_DAYS)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant());
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Attempting to parse invalid JWT", e);
        } catch (Exception e) {
            log.error("Exception has been thrown while validating JWT", e);
        }
        return false;
    }

    public static String getLoginFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public static String extractJwtFromHeader(String authorizationHeader) {
        return StringUtils.hasText(authorizationHeader)
                ? authorizationHeader.replaceFirst("Bearer ", "")
                : null;
    }
}
