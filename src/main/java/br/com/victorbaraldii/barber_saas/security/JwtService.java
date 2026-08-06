package br.com.victorbaraldii.barber_saas.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "barber-saas-secret-key-super-safe-2026-barber-system";

    private static final long EXPIRATION =  1000 * 60 * 60 * 24;

    private SecretKey getKey(){

        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email){

        Date now = new Date();

        Date expiration = new Date(now.getTime() + EXPIRATION);

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getKey())
                .compact();
    }

    public String extractUserName(String token){

        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token){

        try {
            getClaims(token);

            return true;
        } catch (Exception e) {

            return false;
        }
    }

    private Claims getClaims(String token){

        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
