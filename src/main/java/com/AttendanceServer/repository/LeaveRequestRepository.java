package com.AttendanceServer.repository;



import com.AttendanceServer.model.entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    Optional<LeaveRequest> findByEmployeeIdAndProjectIdAndDate(Long employeeId, Long projectId, LocalDate now);
    List<LeaveRequest> findAllByEmployeeId(Long employeeId);
}
