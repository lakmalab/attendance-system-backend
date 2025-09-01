package com.AttendanceServer.service;

import com.AttendanceServer.enums.UserRole;
import io.jsonwebtoken.Claims;


public interface JwtService {
    String generateToken(String email, UserRole role, String clientId) ;

    Claims extractClaims(String token) ;

    String extractEmail(String token);

    String extractRole(String token);

    boolean isTokenValid(String token) ;
}
