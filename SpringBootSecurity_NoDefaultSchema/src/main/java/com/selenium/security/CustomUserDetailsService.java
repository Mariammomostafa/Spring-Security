package com.selenium.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.selenium.dao.CustomerDao;
import com.selenium.model.Customer;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	
	
	private CustomerDao customerDao;
	
	@Lazy
	public CustomUserDetailsService(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<Customer> customers = customerDao.findByUsername(username);
		
		if(customers.get(0) == null) {
			
			throw new UsernameNotFoundException("User does NOT exists ...");
		}
		
		
		
		return new CustomUserDetails(customers.get(0));
	}

}
