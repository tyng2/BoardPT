package com.board.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.headers().frameOptions().disable();
		//H2 콘솔 브라우저 연결 거부 해제
		
		http.formLogin()
				.loginPage("/login.do")
//				.loginProcessingUrl("/loginProcess.do")
				.usernameParameter("id")
				.passwordParameter("pw")
				.successForwardUrl("/")
				.failureForwardUrl("/login.do");
//		http.authorizeRequests().anyRequest().authenticated();
		
		http.authorizeRequests()
				.antMatchers("/**").permitAll();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource)
//				.passwordEncoder(getPasswordEncoder())
//				.usersByUsernameQuery("SELECT id, password, name, email, address, reg_date FROM users WHERE id = ?");
	}


	@Bean
	public static BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
