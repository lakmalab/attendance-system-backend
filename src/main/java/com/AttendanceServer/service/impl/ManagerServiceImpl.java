package com.AttendanceServer.service.impl;

import com.AttendanceServer.enums.UserRole;
import com.AttendanceServer.model.dto.UserDto;
import com.AttendanceServer.model.entities.User;
import com.AttendanceServer.repository.UserRepository;
import com.AttendanceServer.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllEmployeesByProject(Long projectID) {
       return userRepository.findAllByProjectIdAndUserRole(projectID, UserRole.EMPLOYEE).stream()
               .map(User::getDto)
               .collect(Collectors.toList());
    }
}
