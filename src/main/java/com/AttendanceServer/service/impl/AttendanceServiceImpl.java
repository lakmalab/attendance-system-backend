package com.AttendanceServer.service.impl;

import com.AttendanceServer.enums.UserRole;
import com.AttendanceServer.model.dto.AttendanceDTO;
import com.AttendanceServer.model.dto.LeaveRequestDto;
import com.AttendanceServer.model.entities.Attendance;
import com.AttendanceServer.model.entities.LeaveRequest;
import com.AttendanceServer.model.entities.Project;
import com.AttendanceServer.model.entities.User;
import com.AttendanceServer.repository.AttendanceRepository;
import com.AttendanceServer.repository.LeaveRequestRepository;
import com.AttendanceServer.repository.ProjectRepository;
import com.AttendanceServer.repository.UserRepository;
import com.AttendanceServer.service.AttendanceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRequestRepository leaveRequestRepository;
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

    @Override
    public LeaveRequestDto applyLeave(LeaveRequestDto dto) {
        Optional<LeaveRequest> optionalLeaveRequest = leaveRequestRepository.findByEmployeeIdAndProjectIdAndDate(
                dto.getEmployeeId(), dto.getProjectId(), LocalDate.now()
        );
        if (optionalLeaveRequest.isEmpty()) {
            Optional<User> optionalEmployee = userRepository.findById(dto.getEmployeeId());
            Optional<User> optionalManager  = userRepository.findByProjectIdAndUserRole (dto.getProjectId(), UserRole.MANAGER);
            Optional<Project> optionalProject    = projectRepository.findById (dto.getProjectId());

            if(optionalEmployee.isPresent() && optionalManager.isPresent() && optionalProject.isPresent()){
                LeaveRequest leaveRequest =  new LeaveRequest();
                leaveRequest.setDate(LocalDate.now());
                leaveRequest.setEmployee(optionalEmployee.get());
                leaveRequest.setManager(optionalManager.get());
                leaveRequest.setProject(optionalProject.get());
                leaveRequestRepository.save(leaveRequest);
                return modelMapper.map(leaveRequest, LeaveRequestDto.class);
            }else {
                throw new EntityNotFoundException("Entitiy not found");
            }

        } else {
            throw new EntityNotFoundException("Leave Already Applied For Today");
        }
    }
    @Override
    public List<LeaveRequestDto> getAllEmployeeLeave(Long id) {
        return leaveRequestRepository.findAllByEmployeeId(id).stream()
                .map(LeaveRequest::getDto).collect(Collectors.toList());
        }
    }

