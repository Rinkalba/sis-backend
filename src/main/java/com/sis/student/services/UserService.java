package com.sis.student.services;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sis.student.entity.Employee;
import com.sis.student.proxies.EmployeeDto;

@Service
public interface UserService {
	public boolean registerWithDetails(MultipartFile file,EmployeeDto stu);
	public EmployeeDto Details(String id) throws NotFoundException;
	public EmployeeDto updateUserDetails(String id,MultipartFile file,EmployeeDto dto) throws NotFoundException;
	public List<EmployeeDto> findAll();
	public boolean deleteUser(String id);
	
	public byte[] getImg(String email);
	
}
