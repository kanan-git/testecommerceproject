package com.firstjavaapp.ecommerce.service;

import com.firstjavaapp.ecommerce.entity.User;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

public interface JwtService {
    public SecretKey getSigningKey();
    public String generateToken(User user);
    public String extractUsername(String token);
    public boolean isTokenValid(String token, User user);
    public boolean isTokenExpired(String token);
    public Date extractExpiration(String token);
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    public Claims extractAllClaims(String token);
}
