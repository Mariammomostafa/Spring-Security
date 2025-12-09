package com.selenium.springMvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.selenium.springMvc.dao.SignupDao;
import com.selenium.springMvc.dto.SignupDto;

@Controller
public class SignController {
	
	
	@Autowired
	private SignupDao signupDao;
	
	@GetMapping("/registerForm")
	public String signup(@ModelAttribute("signupDto") SignupDto dto) {
		
		return "signup";
	}
	
	
	@PostMapping("/process-register")
	public String processRegister(SignupDto dto) {
		
		signupDao.save(dto);
		
		return "redirect:/login";
	}
	
	@GetMapping("/loginForm")
	public String loginAndSignup() {
		
		return "login";
	}
	
}
