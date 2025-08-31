package com.AttendanceServer.model.entities;


import com.AttendanceServer.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import com.AttendanceServer.model.dto.UserDto;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private String name;

    private UserRole userRole;


    @ManyToOne
    private Project project;


    public UserDto getDto() {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setUserRole(userRole);
        userDto.setEmail(email);

        if(project != null){
            userDto.setProjectId(project.getId());
            userDto.setName(project.getName());
        }

        return userDto;
    }
}
