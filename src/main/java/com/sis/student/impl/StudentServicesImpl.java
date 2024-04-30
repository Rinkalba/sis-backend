package com.sis.student.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sis.student.entity.Employee;
import com.sis.student.proxies.EmployeeDto;
import com.sis.student.repo.StudentRepo;
import com.sis.student.services.UserService;
import com.sis.student.utils.Helper;

@Component
public class StudentServicesImpl implements UserService {

	@Autowired
	private StudentRepo services;
	
	@Autowired
	private Helper helper;
	
	
	@Override
	public boolean registerWithDetails(MultipartFile file,EmployeeDto stuDto) {
	   System.err.println("IMPL"+file+stuDto);
		String uploadFile = helper.uploadFile(file);
		if(uploadFile.equals("Failed to upload file")) {
			return false;
		}else {
			
			Employee sEntity = helper.convertSDtoToS(stuDto);
			sEntity.setProfileImg(uploadFile);
			
			
			if(sEntity!=null) {
				
				services.save(sEntity);
				return true;
			}
		}
		return false;
	}


	@Override
	public EmployeeDto Details(String id) throws NotFoundException {
		
	    Optional<Employee> student = services.findById(id);
	    if(student.isPresent()) {
	    	Employee stu= student.get();
	    	return helper.convertSToSDto(stu);
	    }else {
	    	throw new NotFoundException();
	    }
	}


	@Override
	public EmployeeDto updateUserDetails(String id,MultipartFile file,EmployeeDto dto) throws NotFoundException {
		 Optional<Employee> existingOptional = services.findById(id);
		 System.err.println(dto);
		    if (existingOptional.isPresent()) {
		        Employee existingStudent = existingOptional.get();
		        
		        
		       
		        if (dto.getFullName() != null) {
		            existingStudent.setFullName(dto.getFullName());
		        }
		       
		        if(dto.getContact()!=null) {
		        	existingStudent.setContact(dto.getContact());
		        }
		        if(dto.getDob()!=null) {
		        	existingStudent.setDob(dto.getDob());
		        }
		      
		        if(dto.getAddress()!=null) {
		        	existingStudent.setAddress(dto.getAddress());
		        }
		      
		        if(dto.getBranch()!=null) {
		        	existingStudent.setBranch(dto.getBranch());
		        }
		        if(dto.getExp()!=null) {
		        	existingStudent.setExp(dto.getExp());
		        }
		        if(dto.getYop()!=null) {
		        	existingStudent.setYop(dto.getYop());
		        }
		        if(dto.getCgpa()!=0) {
		        	existingStudent.setCgpa(dto.getCgpa());
		        }
		        if(dto.getCompany()!=null) {
		        	existingStudent.setCompany(dto.getCompany());
		        }
		        if(dto.getDesignation()!=null) {
		        	existingStudent.setDesignation(dto.getDesignation());
		        }
		        if(file!=null) {
		        	MultipartFile profileImg = file;
		        	String uploadFile = helper.uploadFile(profileImg);
		        	System.err.println("uploaded file"+uploadFile);
		        	existingStudent.setProfileImg(uploadFile);
		        	System.err.println("after update"+existingStudent.getProfileImg());
		        }
		        
		       
		        Employee updatedStudent = services.save(existingStudent);
		        
		        
		        return helper.convertSToSDto(updatedStudent);
		    } else {
		    	throw new NotFoundException();
		    }
	}


	@Override
	public List<EmployeeDto> findAll() {
		List<Employee> all = (List<Employee>) services.findAll();
		
		
		List<EmployeeDto> dtos=new ArrayList<>();
		for (Employee student : all) {
			String profileImg = student.getProfileImg();
			System.err.println("profile img"+profileImg);
			byte[] file = helper.readFile(profileImg);
			//System.err.println(file);
			
			
		 
		     //MultipartFile multipartFile =  helper.convertToMultipartFile(file, profileImg);
			
			//MultipartFile fileAsMultipartFile = helper.readFileAsMultipartFile(profileImg);
	        EmployeeDto dto =helper.convertSToSDto(student);
	        //dto.setImg(file.toString());
	        
	        dtos.add(dto);
	        System.err.println("DTO"+dto);
		}
		return dtos;
	}


	@Override
	public boolean deleteUser(String id) {
		services.deleteById(id);
		return true;
	}


	@Override
	public byte[] getImg(String email) {
		Optional<Employee> byId = services.findById(email);
		
		if(byId.isPresent()) {
			Employee employee = byId.get();
			String profileImg = employee.getProfileImg();
			byte[] file = helper.readFile(profileImg);
			return file;
			
		}
		return null;
	}


	
	
	
	



}
