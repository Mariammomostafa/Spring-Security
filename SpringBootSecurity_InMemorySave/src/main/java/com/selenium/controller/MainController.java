package com.selenium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.selenium.dto.SignupDto;


@Controller
public class MainController {

	@Autowired
	private InMemoryUserDetailsManager userDetailsManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/hi")
	public String hello() {
		
		return "welcome";
	}

	@GetMapping("/login")
	public String login() {		
		return "login";
	}
	
	@GetMapping("/register")
	public String signUp(@ModelAttribute("userDto") SignupDto dto) {	
		return "register";
	}
	
	@PostMapping("/process-register")
	public String processRegister(SignupDto dto) {
		
		UserDetails user = User.withUsername(dto.getUsername())
			    .password(passwordEncoder.encode(dto.getPassword()))
			    .roles("USER")
			    .build();	
		userDetailsManager.createUser(user);
		return "redirect:/login";
	}
}
