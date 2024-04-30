package com.sis.student.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	private String emailId;
	
	
	private String fullName;
	
	
	private LocalDate dob;
	private String contact;
	private String address;
	

	private String branch;
	private Integer yop;
	private float cgpa;
	
	private Integer exp;
	private String company;
	private String designation;
	
	private String profileImg;

	
}
