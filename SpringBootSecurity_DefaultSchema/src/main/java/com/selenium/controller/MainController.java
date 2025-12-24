package com.selenium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.selenium.dao.UserDao;
import com.selenium.dto.UserDto;

@Controller
public class MainController {

	
	@Autowired
	private UserDao userDao;
	
	
	@GetMapping("/hi")
	public String hello() {
		
		return "welcome";
	}

	@GetMapping("/login")
	public String login() {
			
		return "login";
	}
	
	@GetMapping("/register")
	public String signUp(@ModelAttribute("userDto") UserDto dto) {
			
		return "register";
	}
	
	
	@PostMapping("/process-register")
	public String processRegister(UserDto dto) {
			
		userDao.insertUser(dto);
		
		return "redirect:/login";
	}
}
