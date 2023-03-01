package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Project;
import com.example.service.ProjectService;

@RestController
public class ProjectController {
	@Autowired
	ProjectService projectService;
	
	@PostMapping("/addProject")
	public Project addProject(@RequestBody Project project){
		return projectService.addProjectDetails(project);		
	}
	
	@GetMapping("/project/{id}")
	public Project getProject(@PathVariable int id) {
		return projectService.getProjectDetailsWithId(id);
	}
	
	@PutMapping("/assignEmpToProject")
	public ResponseEntity<Project> assignEmpToProject(int projectId, int employeeId){
		Project addedProject = projectService.assignEmpToProject(projectId,employeeId);
		return new ResponseEntity<Project>(addedProject, HttpStatus.CREATED);
	}
	
	@PutMapping("updateProject")
	public String updateProject(@RequestBody Project project) {
		return projectService.updateProjectDetails(project);
	}
	
	@DeleteMapping("/delProject/{id}")
	public String deleteProject(@PathVariable int id) {
		return projectService.deleteProjectWithId(id);
	}

}
