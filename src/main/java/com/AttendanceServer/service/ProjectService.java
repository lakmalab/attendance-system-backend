package com.AttendanceServer.service;



import com.AttendanceServer.model.dto.ProjectDTO;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface ProjectService {
     ProjectDTO addProject(ProjectDTO dto);
     List<ProjectDTO> getAllProjects();
}
