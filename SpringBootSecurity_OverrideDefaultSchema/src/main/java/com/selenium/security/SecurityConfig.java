package com.selenium.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.selenium.filter.NoCacheFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	DriverManagerDataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring-jpa-hibernate");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		return dataSource;
	}
	
	@Bean
	  public UserDetailsService userDetailsService() {
	
			JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource());
	
			jdbcUserDetailsManager.setUsersByUsernameQuery("select username , password , enabled from customer where username = ?");
			jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username , authorities from customer where username = ?");
			return jdbcUserDetailsManager;
		}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		http.authorizeHttpRequests(req -> {
			
			req.requestMatchers("/register", "/process-register","/WEB-INF/view/**").permitAll()
			.anyRequest().authenticated();
			
		});
		http.formLogin(custom -> {
			
			custom.loginPage("/login")
			.loginProcessingUrl("/login-processing")
			.permitAll();
			
		});
		http.logout(custom ->{
			
			custom.logoutUrl("/customlogout").permitAll()
			.logoutSuccessUrl("/login?logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
			
		});
		
		http.addFilterBefore(new NoCacheFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

}
