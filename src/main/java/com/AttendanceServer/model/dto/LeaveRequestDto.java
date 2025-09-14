package com.AttendanceServer.model.dto;

import com.AttendanceServer.model.entities.Project;
import com.AttendanceServer.model.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;



@Data
public class LeaveRequestDto {

    private Long id;

    private LocalDate date;

    private Boolean status;

    private Long employeeId;
    private String employeeName;

    private Long projectId;
    private String projectName;

    private Long managerId;
    private String managerName;
}
