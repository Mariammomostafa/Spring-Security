package com.selenium.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.selenium.filter.NoCacheFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	// in case we add static Users without Register form

//	@Bean
//	  public InMemoryUserDetailsManager userDetailsService() {
//				
//		
//			UserDetails user1 = User.withUsername("mrmr")
//			    .password(passwordEncoder().encode("mrmr123"))
//			    .roles("USER")
//			    .build();
//			
//	
//			UserDetails user2 = User.withUsername("ganna")
//				    .password(passwordEncoder().encode("ganna123"))
//				    .roles("USER")
//				    .build();
//			
//			return new InMemoryUserDetailsManager();
//		}
//	
	
	
	
	// in case we add Users Dynamically using the Register form 
	@Bean
	  public InMemoryUserDetailsManager userDetailsService() {
						
			return new InMemoryUserDetailsManager();
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
			.clearAuthentication(true)
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
