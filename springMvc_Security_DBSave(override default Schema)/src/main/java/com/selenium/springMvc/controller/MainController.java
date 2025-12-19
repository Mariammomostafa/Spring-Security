package com.selenium.springMvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.selenium.springMvc.Dao.CustomerDao;
import com.selenium.springMvc.dto.SignupDto;

@Controller
public class MainController {

	
	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping("/login")
	public String login() {
	
		System.out.println(" inside login");
		
		return "login";
	}
	
	@GetMapping("/register")
	public String signup(@ModelAttribute("signupDto") SignupDto dto) {
		
		return "signup";
	}
	
	
	
	@PostMapping("/process-register")
	public String processSignup(SignupDto signupDto) {
		
		System.out.println(" inside processSignup");
		
		customerDao.saveCustomer(signupDto);
		
		return "redirect:/login";
	}

	@RequestMapping("/test")
	public String test() {
		
		return "welcome";
	}
	
}
