package com.selenium.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.selenium.dto.UserDto;


@Component
public class UserDao {
	
	@Autowired
	private JdbcTemplate  jdbcTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void insertUser(UserDto dto) {
		
		String sql1 ="insert into users values(? , ? ,?)";
		String sql2 ="insert into authorities values(? , ? )";
		Object[] args = {dto.getUsername() 
				, passwordEncoder.encode(dto.getPassword()) 
				, 1};
		
		int updateRows = jdbcTemplate.update(sql1, args);
		                 jdbcTemplate.update(sql2, dto.getUsername() , "ROLE_USER");
		
	System.out.println(updateRows + " row/s inserted Successfully...");
		
	}
	
	
	

}
