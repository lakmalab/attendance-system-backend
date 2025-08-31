package service;

import enums.UserRole;
import jakarta.annotation.PostConstruct;
import model.dto.UserDto;
import model.entities.User;

import java.util.Optional;

public interface AuthService {
    UserDto login(UserDto user);
}
