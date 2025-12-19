package com.selenium.springMvc.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.selenium.springMvc.dto.SignupDto;


@Repository
public class CustomerDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public void saveCustomer(SignupDto dto) {
		
		String sql = "insert into customer values (? , ? , ? , ? , ?)";
		
		Object[] args = {dto.getUsername() ,passwordEncoder.encode(dto.getPassword()) , dto.getEmail() , 1 , "ROLE_USER"};
		
		int insertedRow = jdbcTemplate.update(sql, args);
		
		System.out.println(insertedRow + " row/s inserted Successfully ....");
		
	}

}
