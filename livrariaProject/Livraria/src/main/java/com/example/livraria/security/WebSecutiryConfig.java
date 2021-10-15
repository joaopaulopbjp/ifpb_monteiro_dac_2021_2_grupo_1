package com.example.livraria.security;

// import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecutiryConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	// @Autowired
	// private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.GET, "/home").permitAll()
			.antMatchers("/cadastrar-livro").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin().defaultSuccessUrl("/home", true).permitAll()
			.and()
			.logout(logout -> logout.logoutUrl("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth.userDetailsService(userDetailsServiceImpl)
			.passwordEncoder(encoder);
		// auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder);
	}
	
	
}
