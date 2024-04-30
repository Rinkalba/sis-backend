package com.sis.student.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sis.student.entity.ByteArrayMultipartFile;
import com.sis.student.entity.Employee;
import com.sis.student.proxies.EmployeeDto;
import org.springframework.util.StringUtils;

@Component
public class Helper {
	
	@Autowired
	private ObjectMapper mapper;
	
	public Employee convertSDtoToS(EmployeeDto dto) {
		return mapper.convertValue(dto, Employee.class);	
	}
	
	public EmployeeDto convertSToSDto(Employee stu) {
		return mapper.convertValue(stu, EmployeeDto.class);
	}
	
	public final String UPLOAD_DIR=new ClassPathResource("image").getFile().getAbsolutePath();
	
	Helper() throws IOException{
		
	}
	
	public String uploadFile(MultipartFile file) {
		boolean f=false;
		try {
//			InputStream inputStream = file.getInputStream();
//			byte data[]=new byte[inputStream.available()];
//			inputStream.read(data);
//			
//			//write
//			FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+"\\"+file.getOriginalFilename());
//			fos.write(data);
//			
//			fos.close();
			//Path tempFilePath = Files.createTempFile("uploaded_file_", null);
			 //System.err.println(UPLOAD_DIR);
			 //Files.deleteIfExists(tempFilePath);
			
//			Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR, file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//	          return file.getOriginalFilename();
			 String originalFilename = file.getOriginalFilename();
	            String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
	            String uniqueFilename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + extension;

	            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR, uniqueFilename), StandardCopyOption.REPLACE_EXISTING);
	            return uniqueFilename;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
	            return "Failed to upload file";
		}
		
		
	}
	public byte[] readFile(String filePath) {
        try {
            Path path = Paths.get(UPLOAD_DIR+"\\"+filePath);
            System.err.println(path);
            byte[] allBytes = Files.readAllBytes(path);
            System.err.println("ALL BYTES FROM HELPER"+allBytes);
            return allBytes;
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0]; 
        }
    }
	
	public MultipartFile readFileAsMultipartFile(String filePath) {
	    try {
	        Path path = Paths.get(UPLOAD_DIR, filePath);
	        System.err.println(path);
	        byte[] fileBytes = Files.readAllBytes(path);
	        String fileName = StringUtils.getFilename(filePath);
	        System.err.println(fileName);
	        return new ByteArrayMultipartFile(fileBytes, fileName);
	    } catch (IOException e) {
	        e.printStackTrace();
	        // Handle the exception accordingly
	        return null;
	    }
	}
	
	public EmployeeDto jasonData(String empDetails) {
		EmployeeDto customerInfo = null;
		
		try {
			customerInfo = mapper.readValue(empDetails,EmployeeDto.class);
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerInfo;
		
	}
	
	public MultipartFile convertToMultipartFile(byte[] fileBytes, String filename) {
	    return new ByteArrayMultipartFile(fileBytes, filename);
	}}
	
	
	
	
	
