package com.sis.student.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;
 
@Component
public class JwtFilter extends GenericFilterBean {
 
	private final RestTemplate restTemplate;
 
	public JwtFilter(RestTemplate restTemplate) {
		this.restTemplate=restTemplate;
	}
 
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Hello from student jwt filter");
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		final String authHeader = request.getHeader("Authorization");
		System.err.println("Header ===> "+authHeader);
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			throw new ServletException("An exception occurred");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authHeader);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> req = new HttpEntity<>(null, headers);
		System.err.println(req);
 
		Boolean validate = restTemplate.postForObject("http://SERVER-AUTH/auth/auth/validate", req, Boolean.class);
 
		System.err.println(validate);
		if (validate != null && validate) {
			chain.doFilter(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
}