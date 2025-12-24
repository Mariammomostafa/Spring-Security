package com.selenium.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.selenium.dto.CustomerDto;
import com.selenium.model.Customer;

@Component
public class CustomerDao {
	
	@Autowired
	private JdbcTemplate  jdbcTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void insertCustomer(CustomerDto dto) {
		
		String sql ="insert into customer values(? , ? ,? ,? ,?)";
		Object[] args = {dto.getUsername() 
				, passwordEncoder.encode(dto.getPassword()) 
				, dto.getEmail() 
				, 1 
				, "ROLE_USER"};
		
		int updateRows = jdbcTemplate.update(sql, args);
		System.out.println(updateRows + " row/s inserted Successfully...");
		
	}
	
	
	public List<Customer> findByUsername(String name){
		
		String sql = "select * from customer where username =?";
		Object[] args = {name};
		List<Customer> list = jdbcTemplate.query(sql
				         , ps -> ps.setString(1, name)
				         ,  BeanPropertyRowMapper.newInstance(Customer.class));
		
		return list ;
	}
	
	

}
