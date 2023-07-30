package com.example.creatorconnectbackend.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.function.Function;

// Annotate the class as a Spring component.
@Component
public class JwtUtil {

    // Read the JWT secret from application properties.
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    // Extract the email (used as a subject in this JWT implementation) from the token.
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract the expiration date from the token.
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Generic method to extract a particular claim from the token.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from a token.
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Check if the JWT token has expired.
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Public method to generate a token for a given email.
    public String generateToken(String email) {
        return createToken(email);
    }

    // Generate a JWT for the given email, with a set expiration time (10 hours in this implementation).
    private String createToken(String email) {
        return Jwts.builder().setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    // Validate the token: checks that the token's subject (email) matches the provided email and the token hasn't expired.
    public Boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }
}
