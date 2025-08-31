package com.AttendanceServer.service;

import com.AttendanceServer.model.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserDto login(UserDto user);
}
