package com.AttendanceServer.service.impl;

import com.AttendanceServer.model.dto.ProjectDTO;
import com.AttendanceServer.model.entities.Project;

import com.AttendanceServer.repository.ProjectRepository;
import com.AttendanceServer.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {


    private final ProjectRepository projectRepository;

    public ProjectDTO addProject(ProjectDTO dto){
        Project project = new Project();
        project.setName(dto.getName());
        project.setDuration(dto.getDuration());
        project.setStartDate(dto.getStartDate());

        return (ProjectDTO) projectRepository.save(project).getDto();
    }


    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(project -> (ProjectDTO) project.getDto())
                .collect(Collectors.toList());
    }


}
