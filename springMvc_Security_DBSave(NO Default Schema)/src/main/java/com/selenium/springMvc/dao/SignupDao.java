package com.selenium.springMvc.dao;

import java.util.List;

import com.selenium.springMvc.dto.SignupDto;
import com.selenium.springMvc.model.Customer;

public interface SignupDao {

	public void save(SignupDto dto);
	
	public List<Customer> findByUsername(String name);
}
