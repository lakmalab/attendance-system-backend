package com.AttendanceServer.service;


import com.AttendanceServer.model.dto.UserDto;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface AdminService {
     UserDto createUser(UserDto dto);

     List<UserDto> getAllManagers();

     List<UserDto> getAllEmployees();
}
