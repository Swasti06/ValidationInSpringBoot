package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dto.EmployeeDTO;
import com.example.dto.EmployeeRequestDTO;
import com.example.dto.ProjectDTO;
import com.example.entity.Employee;
import com.example.exception.EmployeeAlreadyExistsException;
import com.example.exception.EmployeeNotAvailableException;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	RestTemplate restTemplate;

	public Employee addEmployee(EmployeeRequestDTO employeeDTO) {
		Employee employee=employeeDTO.convertToEntity();
		Optional<Employee> findById = empRepo.findById(employee.geteId());
		if(findById.isPresent()) {
			throw new EmployeeAlreadyExistsException("Employee already exists");
		}
		return empRepo.save(employee);
	}

	public Employee getEmployeeDetailsFromDB(int id) {
		Optional<Employee> optionalProject = empRepo.findById(id);
		Employee employeeDetails = optionalProject
				.orElseThrow(() -> new EmployeeNotAvailableException("Employee does not exist"));
		return employeeDetails;
	}

	public EmployeeDTO assignProjectToEmployee(int projectId, int employeeId) {
		if(projectId==0 && employeeId==0) {
			throw new EmployeeNotAvailableException("Either ProjectId or EmployeeId is null");
		}
		Optional<Employee> optionalEmp = empRepo.findById(employeeId);
		Employee employee = optionalEmp.orElseThrow(()-> new EmployeeNotAvailableException("Employee not available"));
		
		EmployeeDTO employeeDTO=employee.convertToDTO();
		ResponseEntity<ProjectDTO> exchange=restTemplate.exchange("http://project-service/assignEmpToProject?projectId={projectId}&employeeId={employeeId}",
				HttpMethod.PUT,null,ProjectDTO.class,projectId,employeeId);
		ProjectDTO projectDTO=exchange.getBody();
		employeeDTO.setProjectDTO(projectDTO);
		return employeeDTO;
	}

	public String updateExistingEmployee(Employee employee) {
		return null;
	}

	

	
}
