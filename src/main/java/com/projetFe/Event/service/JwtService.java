package com.projetFe.Event.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.Date;
import java.util.function.Function;


@Service
public class JwtService {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 heures
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // génère automatiquement une clé secrète aléatoire (avec l’algorithme HMAC-SHA256)
    /*private Key getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }*/
    // Générer un token
    public  String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // email
                .setIssuedAt(new Date()) //DATE ACTUEL
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key) // signWith(key) utilise une clé secrète HMAC-SHA pour signer le token.
                 //.signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    // Extraire l'email du token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Valider un token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


