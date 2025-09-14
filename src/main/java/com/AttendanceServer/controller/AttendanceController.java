package com.AttendanceServer.controller;

import com.AttendanceServer.model.dto.AttendanceDTO;
import com.AttendanceServer.model.dto.LeaveRequestDto;
import com.AttendanceServer.model.dto.UserDto;
import com.AttendanceServer.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/attendance")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<?> markAttendance (@RequestBody AttendanceDTO dto){
        try {
            AttendanceDTO attendanceDTO = attendanceService.markAttendance(dto);
            if (attendanceDTO == null){
                return new ResponseEntity<>("Wrong Conditional", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(attendanceDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/applyLeave")
    public ResponseEntity<?> applyLeave (@RequestBody LeaveRequestDto dto){
        try {
            LeaveRequestDto leaveRequestDto = attendanceService.applyLeave(dto);
            if (leaveRequestDto == null){
                return new ResponseEntity<>("Wrong Conditional", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(leaveRequestDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
