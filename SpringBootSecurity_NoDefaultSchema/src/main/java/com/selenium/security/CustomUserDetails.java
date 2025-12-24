package com.selenium.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.selenium.model.Customer;

public class CustomUserDetails implements UserDetails{

	private Customer customer;
	
	public CustomUserDetails(Customer customer) {
		super();
		this.customer = customer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(customer.getAuthorities()));
		
		return authorities;
	}

	@Override
	public @Nullable String getPassword() {
		
		return customer.getPassword();
	}

	@Override
	public String getUsername() {

		return customer.getUsername();
	}

}
