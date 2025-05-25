package com.ams.dev.api.spring.security.service.auth;

import com.ams.dev.api.spring.security.persistence.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.token.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken(UserDetails user, Map<String,Object> extraClaims) {

        Date issueAt = new Date(System.currentTimeMillis());
        Date expiration = new Date( (EXPIRATION_IN_MINUTES * 60 * 1000)+issueAt.getTime());
        String jwt = Jwts.builder()
                //header
                .header()
                .type("JWT")
                .and()
                .subject(user.getUsername())
                .issuedAt(issueAt)
                .expiration(expiration)
                .claims(extraClaims)
                .signWith(generateKey(),Jwts.SIG.HS256)
                .compact();

        return jwt;
    }

    private SecretKey generateKey() {
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(passwordDecoded);
    }

    public String extractUsername(String jwt) {
        //extraer todos los claims pararecueperarel username
        return extratAllClaims(jwt).getSubject();
    }

    private Claims extratAllClaims(String jwt) {
        return Jwts.parser().verifyWith(generateKey()).build()
                .parseSignedClaims(jwt).getPayload();
    }
}
