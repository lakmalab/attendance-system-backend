package com.AttendanceServer.model.dto;

import com.AttendanceServer.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private long id;

    private String email;

    private String password;

    private String name;

    private UserRole userRole;

    private Long projectId;

    private String projectName;
}
