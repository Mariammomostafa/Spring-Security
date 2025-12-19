package com.selenium.springMvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
	
	
	@Autowired
	private UserDetailsService userDetailsService; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		
		return provider;
	}
	
	//override default SecurityFilterChain to permit some requests to NOT be authenticated ,create custom login & logout 
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.
		     authorizeHttpRequests(auth -> {
		    	 auth.requestMatchers("/hi" , "/registerForm" , "/process-register" , "/loginForm").permitAll()
		    	 .requestMatchers(("/WEB-INF/view/**")).permitAll()
		    	 .requestMatchers("/user").hasAuthority("ROLE_USER")
		    	 .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
		    	 .anyRequest().authenticated();
		     });
		http.formLogin(form -> {
			
			form.loginPage("/login")
			    .loginProcessingUrl("/login-processing")
			    .permitAll();
			    
		});
		
		http.logout(logout ->
        logout
        .logoutUrl("/customlogout").permitAll()
        .logoutSuccessUrl("/login?logout") 
        .invalidateHttpSession(true) // Invalidate the HTTP session
        .deleteCookies("JSESSIONID"));
	
		http.exceptionHandling(exp -> exp.accessDeniedPage("/exceptionHandle"));
		    return http.build();
	}

}
