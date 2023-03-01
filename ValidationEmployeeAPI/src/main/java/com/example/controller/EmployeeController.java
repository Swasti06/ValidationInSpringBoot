package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EmployeeDTO;
import com.example.dto.EmployeeRequestDTO;
import com.example.entity.Employee;
import com.example.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@PostMapping("/employee")
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody EmployeeRequestDTO employee) {
		Employee emp = empService.addEmployee(employee);
		return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
	}
	
	@PostMapping("/emp/assignProject")
	public ResponseEntity<EmployeeDTO> assignProject(int projectId, int employeeId) {
		EmployeeDTO empDTO=empService.assignProjectToEmployee(projectId,employeeId);
		return new ResponseEntity<EmployeeDTO>(empDTO,HttpStatus.OK);
	}

	@GetMapping("/employee/{id}")
	public Employee getEmpDetails(int id) {
		return empService.getEmployeeDetailsFromDB(id);
	}

	@PutMapping("updateEmployee")
	public String updateEmployee(@RequestBody Employee employee) {
		return empService.updateExistingEmployee(employee);
	}

}
