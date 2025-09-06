package com.AttendanceServer.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.AttendanceServer.model.dto.UserDto;
import com.AttendanceServer.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;
import com.AttendanceServer.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto user){
        System.out.println(user);
        UserDto dbUser = authService.login(user);

        if (dbUser != null){
            String token = jwtService.generateToken(user.getEmail(), user.getUserRole(), "WEB");
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "user", user
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
