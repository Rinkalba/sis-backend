package com.sis.student.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.sis.student.entity.Employee;
import com.sis.student.proxies.EmployeeDto;
import com.sis.student.services.UserService;
import com.sis.student.utils.Helper;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService services;
	
	@Autowired
	private Helper helper;
	

	
	@Autowired
	private RestTemplate template;
	
	
	
	
	@PostMapping(value="/register",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> registerWithDetailss(@RequestParam(value="file") MultipartFile file,@RequestParam(value="user-info") String stuDto){
//		String value = headerToken;
//		System.err.println(value);
//		HttpHeaders headers=new HttpHeaders();
//		headers.set("Authorization", value);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Object> req = new HttpEntity<>(null, headers);
//		Boolean forObject = template.postForObject("http://SERVER-AUTH/auth/auth/validate", req,Boolean.class);
//		System.err.println(forObject);
//		if(forObject) {
//			
//			boolean registerWithDetails = services.registerWithDetails(stuDto);
//			if(registerWithDetails) {
//			}
//		}
		System.err.println(file);
		System.err.println(stuDto);
		EmployeeDto studetail = helper.jasonData(stuDto);
		if(services.registerWithDetails(file,studetail)) {
			
			return new ResponseEntity<String>("Registered!",HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Something went wrong!",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getEmployee/{id}")
	public EmployeeDto Detailss(@PathVariable("id") String id) throws NotFoundException {
		System.err.println("from user ");
		EmployeeDto details = services.Details(id);
		if(details!=null) {
			return details;
		}
		return null;
	}
	
	@PostMapping("/updateUser/{id}")
	public ResponseEntity<String> updateUserDetailss(@PathVariable("id") String id,@RequestParam(value="file") MultipartFile file,@RequestParam(value="user-info") String stuDto) throws NotFoundException {
		EmployeeDto studetail = helper.jasonData(stuDto);
		EmployeeDto updateUserDetails = services.updateUserDetails(id,file,studetail);
		if(updateUserDetails!=null) {
			return new ResponseEntity<String>("User Updated Succesfully!",HttpStatus.OK);
		}
		return new ResponseEntity<String>("User updation failed!",HttpStatus.BAD_REQUEST);
	}
		
	@GetMapping("/getFeeDetails/{id}")
	public ResponseEntity<Object> getFeeDetailss(@PathVariable("id") String id) throws NotFoundException{
		//String url = "http://localhost:8093/admin/admin/getFeeDetails/{id}";
		String url = "http://SERVER-ADMIN/admin/admin/getFeeDetails/{id}";
        Object object = template.getForObject(url, Object.class, id);
        return new ResponseEntity<>(object, HttpStatus.OK);
		
	}
	

	
	
	@GetMapping("/getAllUsers")
	public List<EmployeeDto> getAllUsers(){
		List<EmployeeDto> all = services.findAll();
		if(!all.isEmpty()) {
			return all;
		}
		return null;
	}
	
	@GetMapping("/getImg/{email}")
	public ResponseEntity<byte[]> getImg(@PathVariable("email") String email){
		byte[] img = services.getImg(email);
		System.err.println("controller"+img);
		return new ResponseEntity<byte[]>(img,HttpStatus.OK);
		
	}
	
	@PostMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		boolean deleteUser = services.deleteUser(id);
		if(deleteUser) {
			return new ResponseEntity<String>("User Deleted Successfully!",HttpStatus.OK);
			
		}else {
			return new ResponseEntity<String>("Something went wrong!!",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
}
