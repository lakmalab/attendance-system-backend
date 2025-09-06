package com.AttendanceServer.service.impl;

import com.AttendanceServer.model.dto.AttendanceDTO;
import com.AttendanceServer.model.dto.UserDto;
import com.AttendanceServer.model.entities.Attendance;
import com.AttendanceServer.repository.AttendanceRepository;
import com.AttendanceServer.repository.ProjectRepository;
import com.AttendanceServer.repository.UserRepository;
import com.AttendanceServer.service.AttendanceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final AttendanceRepository attendanceRepository;
    private final ModelMapper modelMapper;

    @Override
    public AttendanceDTO markAttendance(AttendanceDTO dto) {
       try {
           Attendance attendance = modelMapper.map(dto, Attendance.class);
           attendance.setDate(LocalDate.now());
           if (projectRepository.existsById(attendance.getProject().getId())  && userRepository.existsById(attendance.getEmployee().getId())){
               Attendance saved = attendanceRepository.save(attendance);
               return modelMapper.map(saved, AttendanceDTO.class);
           }else {
               throw new EntityNotFoundException("Related Employee Not Found");
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}
