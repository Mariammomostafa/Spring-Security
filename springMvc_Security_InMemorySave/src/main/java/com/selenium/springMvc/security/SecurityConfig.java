package com.selenium.springMvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
	
	// create users & save them temporary in memory 
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		
		UserDetails admin = User.withUsername("mrmr")
		    .password(passwordEncoder.encode("mrmr123"))
		    .roles("ADMIN").build();
		
		UserDetails user = User.withUsername("ganna")
			    .password(passwordEncoder.encode("ganna123"))
			    .roles("USER").build();
		
		return new InMemoryUserDetailsManager(admin , user);		
	}
	
	//override default SecurityFilterChain to permit some requests to NOT be authenticated ,create custom login & logout 
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.
		     authorizeHttpRequests(auth -> {
		    	 auth.requestMatchers(("/hi")).permitAll()
		    	 .requestMatchers(("/WEB-INF/view/**")).permitAll()
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
	
		    return http.build();
	}

}
