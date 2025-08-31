package controller;

import model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AuthService;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto user){
        UserDto dbUser = authService.login(user);

        if (dbUser == null){
            return new ResponseEntity<>("Wrong Conditional", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(dbUser, HttpStatus.OK);
    }
}
