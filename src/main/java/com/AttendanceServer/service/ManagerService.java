package com.AttendanceServer.service;


import com.AttendanceServer.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ManagerService {

     List<UserDto> getAllEmployeesByProject(Long projectID);
}
