package com.automobile;


<<<<<<< HEAD



=======
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private String SECRET_KEY = "secret";

     public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

     public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

     public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
     private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(SECRET_KEY.getBytes())).parseClaimsJws(token).getBody();
    }

     private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

     public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

     private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
<<<<<<< HEAD
                .setExpiration(new Date(System.currentTimeMillis() + 7*24*60*60*1000)) 
=======
                .setExpiration(new Date(System.currentTimeMillis() + 2*60*60*24*1000)) 
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                .compact();
    }

     public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}