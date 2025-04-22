package com.backend.E_commerce.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider {
//    the same key is used to generate and validate token
    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    public  String generateToken(Authentication auth){
        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+84000000)) // valid for1 day
                .claim("email",auth.getName())
                .signWith(key).compact();
//        .signWith(key) -> This line signs the token with your secret key using HS256 algorithm (default).Ensures no one can tamper with the token without the secret.
//        .compact() -> This method finalizes the building and converts everything into a string — the actual JWT token.

        return jwt;
    }
    public  String getEmailFromToken(String jwt){
        jwt=jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email = String.valueOf(claims.get("email"));
        return email;
    }
}
