package com.AttendanceServer.model.entities;

import com.AttendanceServer.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Data
@Setter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private AttendanceStatus attendanceStatus;

    @ManyToOne
    private Project project;

    @ManyToOne
    private  User employee;

    @ManyToOne
    private User manager;

}
