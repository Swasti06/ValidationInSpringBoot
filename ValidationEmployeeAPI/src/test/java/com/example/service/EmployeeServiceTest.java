package com.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dto.EmployeeRequestDTO;
import com.example.entity.Employee;
import com.example.exception.EmployeeAlreadyExistsException;
import com.example.exception.EmployeeNotAvailableException;
import com.example.repository.EmployeeRepository;
@SpringBootTest
class EmployeeServiceTest {

	@InjectMocks
	EmployeeService empService;
	
	@Mock
	EmployeeRepository empRepo;
	
	@Test
	//this just checks the behaviour of the assign method it has nothing to do with findById
	//it checks if an optional object is assigned even when there is no employee in the database
	void testAssignProjectToEmployeeEmpNotInDB() {
		when(empRepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		assertThrows(EmployeeNotAvailableException.class, ()-> empService.assignProjectToEmployee(11, ArgumentMatchers.anyInt()));
	}
	
	@Test
	void testAssignProjectToEmployeeWithPidEidZero() {
		assertThrows(EmployeeNotAvailableException.class, ()->empService.assignProjectToEmployee(0, 0));
	}
	
	
	@Test 
	void testaddEmpWithEmpAlreadyAvailable() {
		when(empRepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(new Employee()));
		assertThrows(EmployeeAlreadyExistsException.class,()->empService.addEmployee(new EmployeeRequestDTO()));
	}
	
	@Test 
	void testaddEmployeeSuccessUsingNotNull() {
		when(empRepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		when(empRepo.save(ArgumentMatchers.any())).thenReturn(new Employee());
		Employee addEmployee=empService.addEmployee(new EmployeeRequestDTO());
		assertNotNull(addEmployee);
	}
	
	@Test 
	void testaddEmployeeSuccessUsingEquals() {
		when(empRepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		Employee e= new Employee();
		e.seteId(0);
		when(empRepo.save(ArgumentMatchers.any())).thenReturn(new Employee());
		Employee addEmployee=empService.addEmployee(new EmployeeRequestDTO());
		assertNotNull(addEmployee);
	}

}
