package com.AttendanceServer.service.impl;

import com.AttendanceServer.enums.UserRole;
import jakarta.annotation.PostConstruct;
import com.AttendanceServer.model.dto.UserDto;
import com.AttendanceServer.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.AttendanceServer.repository.UserRepository;
import com.AttendanceServer.service.AuthService;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void createAdminUser(){
        User optionalUser = userRepository.findByUserRole(UserRole.ADMIN);

        if (optionalUser == null){
            User user = new User();

            user.setName("Admin");
            user.setEmail("admin@gmail.com");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(passwordEncoder.encode("admin"));

            userRepository.save(user);
            System.out.println("Admin User Created");
        }else {
            System.out.println("Admin User Already Exit");
        }
    }

    public UserDto login(UserDto user){

        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
        if (dbUser.isPresent() && passwordEncoder.matches(user.getPassword(), dbUser.get().getPassword())){
            return dbUser.get().getDto();

        }
        return null;
    }


}
