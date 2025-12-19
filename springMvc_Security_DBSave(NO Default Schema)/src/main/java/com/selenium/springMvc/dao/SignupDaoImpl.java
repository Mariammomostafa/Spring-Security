package com.selenium.springMvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.selenium.springMvc.dto.SignupDto;
import com.selenium.springMvc.model.Customer;

@Repository
public class SignupDaoImpl implements SignupDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void save(SignupDto dto) {
		
		
		String sql = "insert into customer values (? , ? , ? , ? , ? )";	
		Object[] args = { dto.getUsername() , passwordEncoder.encode(dto.getPassword()) , dto.getEmail(),1,"USER"};
		
		int insertedRow = jdbcTemplate.update(sql, args);           
		System.out.println(insertedRow + " row/s inserted Successfully ....");
	}
	
	@Override
	public List<Customer> findByUsername(String name) {
		
		System.out.println(" inside findByUsername() ...");
		
		String sql = "select * from customer where username = ?";	
				
		List<Customer> customer = jdbcTemplate.query(sql,  
				
								ps -> ps.setString(1, name)
						
								, BeanPropertyRowMapper.newInstance(Customer.class));
						
		return customer;
		
	}

}
