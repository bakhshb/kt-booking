package com.kt.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	/**
	 * this method if user trys to access unspecified url it will redirect him
	 * to login page
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/rest/status", "/rest/version", "/home")
				.permitAll().anyRequest().authenticated();
		http.formLogin().loginPage("/login").permitAll().and().logout()
				.permitAll().and().csrf().disable();
	}
	
	@Order(Ordered.HIGHEST_PRECEDENCE)
	@Configuration
	protected static class AuthenticationSecurity extends
			GlobalAuthenticationConfigurerAdapter {

		@Autowired
		private CustomUserDetailsService customUserDetailsService;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(customUserDetailsService);
		}
	}
}