package com.selenium.springMvc.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;

	  //need this been to load the saved users details from DB
	  @Bean
	  public UserDetailsService userDetailsService() {
	
			JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
	
			return jdbcUserDetailsManager;
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
