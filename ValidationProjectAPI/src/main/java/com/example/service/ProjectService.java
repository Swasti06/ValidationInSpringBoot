package com.example.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Project;
import com.example.exceptions.ProjectDoesNotExistException;
import com.example.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepo;

	public Project addProjectDetails(Project project) {
		return projectRepo.save(project);
	}

	public Project getProjectDetailsWithId(int id) {
		Optional<Project> optionalProject = projectRepo.findById(id);
		Project projectDetails = optionalProject
				.orElseThrow(() -> new ProjectDoesNotExistException("Project does not exist"));
		return projectDetails;
	}
	
	@Transactional
	public String updateProjectDetails(Project projectFromPayload) {
		Optional<Project> optionalProject = projectRepo.findById(projectFromPayload.getpId());
		optionalProject.orElseThrow(()-> new ProjectDoesNotExistException("Project does not exist"));
		Project projectFromDB= optionalProject.get();
		if(projectFromPayload.getpName() != null) {
			projectFromDB.setpName(projectFromPayload.getpName());
		}
		if(projectFromPayload.getLocation() != null) {
			projectFromDB.setLocation(projectFromPayload.getLocation());
		}
		if(projectFromPayload.getStartDate() != null) {
			projectFromDB.setStartDate(projectFromPayload.getStartDate());
		}		
		if(projectFromPayload.getEmployeeId() != 0) {
			projectFromDB.setEmployeeId(projectFromPayload.getEmployeeId());
		}
		return "Project details updated";
	}

	public String deleteProjectWithId(int id) {
		Optional<Project> optionalProject = projectRepo.findById(id);
		optionalProject.orElseThrow(()-> new ProjectDoesNotExistException("Project does not exist"));
		projectRepo.deleteById(id);
		return "deleted";
	}
	@Transactional
	public Project assignEmpToProject(int projectId, int employeeId) {
		Optional<Project> optionalProject = projectRepo.findById(projectId);
		Project project = optionalProject.orElseThrow(()-> new ProjectDoesNotExistException("Project does not exist"));
		if(project.getEndDate().compareTo(LocalDate.now())<0) {
			throw new ProjectDoesNotExistException("Project is expired.");
		}
		project.setEmployeeId(employeeId);
		return project;
	}
	
	
	
	
	
	

}
