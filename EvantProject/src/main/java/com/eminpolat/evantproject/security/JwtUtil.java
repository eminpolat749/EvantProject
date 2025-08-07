package com.eminpolat.evantproject.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 256-bit key üretmek için, en az 32 karakter
    private final String SECRET_KEY = "eminbeyinkisiselsupergizlianaharikelime";

    // Token geçerlilik süresi (örnek: 1 gün)
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Token üretim kısmı
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // token içine kullanıcı adını koyuyoruz
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Token'dan kullanıcı adı çekme
    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    // Token geçerli mi kontrolü
    public boolean isTokenValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Token'ı çözümleyen private metot
    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }


}
