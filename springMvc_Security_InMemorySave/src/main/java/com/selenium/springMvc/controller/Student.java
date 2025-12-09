package com.selenium.springMvc.controller;

import org.springframework.stereotype.Component;

@Component
public class Student {

	
	private String name = "ganna";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
