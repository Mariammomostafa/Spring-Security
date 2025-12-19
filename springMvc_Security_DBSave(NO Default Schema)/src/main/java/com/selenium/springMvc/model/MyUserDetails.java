package com.selenium.springMvc.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class MyUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer customer;
		
	public MyUserDetails(Customer customer) {
		super();
		this.customer = customer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> roles = new ArrayList<>();
		
		if ( customer.getAuthorities()!= null && !customer.getAuthorities().isEmpty()) {
		roles.add(new SimpleGrantedAuthority(customer.getAuthorities()));
		}
		return roles;
	}

	@Override
	public String getPassword() {
		
		return customer.getPassword();
	}

	@Override
	public String getUsername() {
		
		return customer.getUsername();
	}

}
