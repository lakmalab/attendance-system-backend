package com.AttendanceServer.model.entities;

import com.AttendanceServer.enums.AttendanceStatus;
import com.AttendanceServer.model.dto.LeaveRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Data
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Boolean status;

    @ManyToOne
    private Project project;

    @ManyToOne
    private  User employee;

    @ManyToOne
    private User manager;
    public LeaveRequestDto getDto(){
        LeaveRequestDto dto = new LeaveRequestDto();

        dto.setId(id);
        dto.setDate(date);
        dto.setStatus(status);

        dto.setProjectId(project.getId());
        dto.setProjectName(project.getName());

        dto.setEmployeeId(employee.getId());
        dto.setEmployeeName(employee.getName());

        dto.setManagerId(manager.getId());
        dto.setManagerName(manager.getName());

        return dto;
    }
}
