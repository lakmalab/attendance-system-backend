package com.AttendanceServer.service.impl;

import com.AttendanceServer.enums.UserRole;
import com.AttendanceServer.model.dto.UserDto;
import com.AttendanceServer.model.entities.Project;
import com.AttendanceServer.model.entities.User;
import com.AttendanceServer.repository.ProjectRepository;
import com.AttendanceServer.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public UserDto createUser(UserDto dto){
        boolean exists = userRepository.findByEmail(dto.getEmail()).isPresent();
        if (exists){
            throw new EntityExistsException("User already exists");
        }
        Optional<Project> optionalProject = projectRepository.findById(dto.getProjectId());
        if (optionalProject.isPresent()){
            User user = new User();
            user.setUserRole(dto.getUserRole());
            user.setPassword(dto.getPassword());
            user.setEmail(dto.getEmail());
            user.setName(dto.getName());
            user.setProject(optionalProject.get());
            User userCreated = userRepository.save(user);
            return userCreated.getDto();
        }
        throw new EntityNotFoundException("Project Not Found");
    }

    public List<UserDto> getAllManagers(){
        List<User> users = userRepository.findAllByUserRole(UserRole.MANAGER);
        return  users.stream().map(User::getDto).collect(Collectors.toList());
    }

    public List<UserDto> getAllEmployees(){
        List<User> users = userRepository.findAllByUserRole(UserRole.EMPLOYEE);
        return  users.stream().map(User::getDto).collect(Collectors.toList());
    }
}
