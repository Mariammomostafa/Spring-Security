package com.selenium.springMvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.selenium.springMvc.dto.SignupDto;

@Repository
public class SignupDaoImpl implements SignupDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void save(SignupDto dto) {
		
		
		String sql = "insert into users values (? , ? , ?)";
		String sql2 = "insert into authorities values (? , ?)";
		
		Object[] args = { dto.getUsername() , passwordEncoder.encode(dto.getPassword()) , 1};
		
		int insertedRow = jdbcTemplate.update(sql, args);
		                  jdbcTemplate.update(sql2, dto.getUsername() ,"USER");
		            
		System.out.println(insertedRow + " row/s inserted Successfully ....");
	}

}
