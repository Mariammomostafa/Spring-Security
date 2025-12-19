package com.selenium.springMvc.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
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
	

	
	// create users & save them permanently  in data base for first run

//	@Bean
//    public UserDetailsService userDetailsService() {
//		
//		
//		 UserDetails user1 =User.builder()
//	        		.username("mrmr")
//	        		.password(passwordEncoder.encode("mrmr123"))
//	        		.roles("ADMIN")
//	        		.build();
//		 
//        UserDetails user =User.builder()
//        		.username("hema")
//        		.password(passwordEncoder.encode("hema123"))
//        		.roles("USER")
//        		.build();
//        		        
//        UserDetails admin = User.withUsername("monna")
//            .password(passwordEncoder.encode("monna123"))
//            .roles("USER")
//            .build();
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(admin);
//        jdbcUserDetailsManager.createUser(user1);     
//    
//        return jdbcUserDetailsManager;
//    }
	
	  // after saved users in DB & run app for the second time , need this been to load the saved users details from DB
	  @Bean
	  public UserDetailsService userDetailsService() {
	
			JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
	
			jdbcUserDetailsManager.setUsersByUsernameQuery("select username , password , enabled from customer where username = ?");
			jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username , authorities from customer where username = ?");
			
			return jdbcUserDetailsManager;
		}
	  
	  @Bean
	  public JdbcTemplate jdbcTemplate() {
		  
		  return new JdbcTemplate(dataSource);
	  }
	
	
	//override default SecurityFilterChain to permit some requests to NOT be authenticated ,create custom login & logout 
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.
		     authorizeHttpRequests(auth -> {
		    	 auth.requestMatchers("/hi" , "/register" , "/process-register").permitAll()
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
