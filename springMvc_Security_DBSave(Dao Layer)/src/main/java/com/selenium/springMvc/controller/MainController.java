package com.selenium.springMvc.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	
	
	@GetMapping("/login")
	public String loginAndSignup() {
		
		return "welcome";
	}
	
	@GetMapping("/user")
	public String userDashBoard(Principal principal , Authentication auth , Model model) {
		
		String name = principal.getName();
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		model.addAttribute("name", name);
		model.addAttribute("roles", authorities);
		
		return "userDashBoard";
	}
	
	@GetMapping("/admin")
	public String adminDashBoard() {
		
		return "adminDashBoard";
	}
	
	@GetMapping("/exceptionHandle")
	public String handleException() {
		
		return "accessDenied";
	}
	
}
