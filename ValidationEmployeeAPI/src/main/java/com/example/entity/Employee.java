package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.dto.EmployeeDTO;

@Entity
public class Employee {
	@Id
	@Column(name = "EmployeeID")
	private int eId;
	@Column(name = "EmployeeName")
	private String eName;
	@Column(name = "EmployeeSalary")
	private int eSalary;
	private String skills;
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

	public EmployeeDTO convertToDTO() {
		EmployeeDTO eDTO = new EmployeeDTO();
		eDTO.seteId(this.geteId());
		eDTO.seteName(this.geteName());
		eDTO.setEmail(this.getEmail());
		eDTO.seteSalary(this.geteSalary());
		eDTO.setSkills(this.getSkills());
		return eDTO;
	}

}
