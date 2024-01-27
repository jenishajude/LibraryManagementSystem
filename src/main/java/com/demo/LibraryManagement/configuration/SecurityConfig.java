package com.demo.LibraryManagement.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder().encode("admin"))
			.roles("ADMIN")
		    .and()
			.withUser("user").password(passwordEncoder().encode("user"))
			.roles("USER");
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
            http.authorizeRequests()
            .antMatchers("/api/books/*", "/api/patrons/**", "/api/borrow/**","/api/return/**").hasRole("ADMIN")
            .antMatchers("/api/books", "/api/patrons").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/swagger-ui/").permitAll()
            .anyRequest().authenticated()
            .and().httpBasic()
            .and().cors().disable().csrf().disable(); 
    }
    
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 

}
