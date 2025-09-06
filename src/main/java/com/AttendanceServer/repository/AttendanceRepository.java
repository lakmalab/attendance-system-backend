package com.AttendanceServer.repository;



import com.AttendanceServer.model.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Project, Long> {
}
