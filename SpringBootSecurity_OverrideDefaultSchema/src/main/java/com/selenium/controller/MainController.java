package com.selenium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.selenium.dao.CustomerDao;
import com.selenium.dto.CustomerDto;

@Controller
public class MainController {

	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping("/hi")
	public String hello() {
		
		return "welcome";
	}

	@GetMapping("/login")
	public String login() {
			
		return "login";
	}
	
	@GetMapping("/register")
	public String signUp(@ModelAttribute("customerDto") CustomerDto dto) {
			
		return "register";
	}
	
	
	@PostMapping("/process-register")
	public String processRegister(CustomerDto dto) {
			
		customerDao.insertCustomer(dto);
		
		return "redirect:/login";
	}
	
}
