package com.spring.main.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

import com.spring.services.UserService;
@Configuration
@EnableWebSecurity
@Order(1)
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;

	protected void configure(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity.cors().and().csrf().disable();
		
		httpSecurity
		
		.antMatcher("/admin/**")
		
		.authorizeRequests()
		
		.antMatchers("/admin-panel/welcome").access("hasRole('ROLE_ADMIN')")
		
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		
		.antMatchers("/api/**").access("hasRole('ROLE_ADMIN')")
		
		.and()
		
		.formLogin().loginPage("/admin-panel/login")
		
		.loginProcessingUrl("/admin/process-login")
		
		.defaultSuccessUrl("/admin-panel/welcome")
		
		.failureUrl("/admin-panel/login?error")
		
		.usernameParameter("username").passwordParameter("password")
		
		.and()
		
		.logout().logoutUrl("/admin-panel/process-logout")
		
		.logoutSuccessUrl("/admin-panel/login?logout")
		
		.deleteCookies("JSESSIONID")
		
		.invalidateHttpSession(true)
		
		.and()
		
		.exceptionHandling().accessDeniedPage("/admin-panel/accessDenied");
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
		
		builder.userDetailsService(userService);
		
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	public SecurityContextHolderAwareRequestFilter awareRequestFilter() {
		
		return new SecurityContextHolderAwareRequestFilter();
		
	}
	
	@Bean
	public SecurityContextPersistenceFilter persistenceFilter() {
		
		return new SecurityContextPersistenceFilter();
		
	}

}