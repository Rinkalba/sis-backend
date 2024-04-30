package com.sis.student.globalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<FormateException> handleNotFoundException(HttpClientErrorException ex) {
		FormateException exc=new FormateException();
		
		exc.setExceptionManager("TOKEN IS NOT VALID!You're Unauthorized User");
		exc.setErrorMsg("404");
		return new ResponseEntity<FormateException>(exc,HttpStatus.BAD_REQUEST);
    }
}
