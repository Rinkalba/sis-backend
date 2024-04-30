package com.sis.student.proxies;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "studentDto")
public class EmployeeDto {
	
	
	private String emailId;
	
	@NotEmpty(message="Full Name required!")
	private String fullName;
	
	@NotEmpty(message="DOB required!")
	private LocalDate dob;
	
	@NotEmpty(message="Contact required!")
	@Max(value=10,message = "Contact Must be 10 characters")
	private String contact;
	
	@NotEmpty(message="Address required!")
	private String address;
	@NotEmpty(message="Experience required!")
	private Integer exp;
	@NotEmpty(message="Branch required!")
	private String branch;
	@NotEmpty(message="YOP required!")
	private Integer yop;
	
	@NotEmpty(message="CGPA required!")
	private float cgpa;
	
	@NotEmpty(message="Companu Name required!")
	private String company;
	@NotEmpty(message="Designation required!")
	private String designation;
	
	@NotEmpty(message="Profile Img required!")
	@JsonIgnore
	private MultipartFile profileImg;
	
	//private byte[] img;


	
//	private FeeDetailsDto feeDetailDto;

}
