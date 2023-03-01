package com.example.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.example.entity.Employee;

public class EmployeeRequestDTO {
	@Max(value=200,message="Employee id cannot be more than 200")
	@Min(value=10,message="Employee id cannot be smaller than 10")
	private int eId;
	@NotBlank(message="namecan not be blank")
	private String eName;
	private int eSalary;
	private String skills;
	@Email(message="Email is invalid")
	private String email;
	public int geteId() {
		return eId;
	}
	public void seteId(int eId) {
		this.eId = eId;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public int geteSalary() {
		return eSalary;
	}
	public void seteSalary(int eSalary) {
		this.eSalary = eSalary;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Employee convertToEntity() {
		Employee employee= new Employee();
		employee.seteId(this.geteId());
		employee.seteName(this.geteName());
		employee.seteSalary(this.geteSalary());
		employee.setSkills(this.getSkills());
		employee.setEmail(this.getEmail());
		return employee;
	}
	
	

}
