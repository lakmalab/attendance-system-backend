package com.AttendanceServer.controller;

import com.AttendanceServer.enums.UserRole;
import com.AttendanceServer.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/managers")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/employees/{projectId}")
    public ResponseEntity<?> getAllEmployeesByProject(@PathVariable Long projectID){
        try{
            return  ResponseEntity.ok(managerService.getAllEmployeesByProject(projectID));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
