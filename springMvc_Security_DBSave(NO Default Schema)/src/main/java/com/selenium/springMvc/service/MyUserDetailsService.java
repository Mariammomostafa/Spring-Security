package com.selenium.springMvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.selenium.springMvc.dao.SignupDaoImpl;
import com.selenium.springMvc.model.Customer;
import com.selenium.springMvc.model.MyUserDetails;


@Service
public class MyUserDetailsService implements UserDetailsService{

	
	private SignupDaoImpl daoImpl;
	
	
	@Lazy
	public MyUserDetailsService(SignupDaoImpl daoImpl) {
		this.daoImpl = daoImpl;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println(" inside loadUserByUsername() ....");
		
		List<Customer> list = daoImpl.findByUsername(username);
		
		System.out.println(list.get(0));

		
		if(list.get(0) == null ) {
			
			throw new UsernameNotFoundException("User Does NOT exists ....");
		}
		
		return new MyUserDetails(list.get(0));
	}

}
