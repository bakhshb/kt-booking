package com.kt.booking;

import org.springframework.context.annotation.Configuration;
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
				.antMatchers("/", "/rest/status", "/rest/status", "/home")
				.permitAll().anyRequest().authenticated();
		http.formLogin().loginPage("/login").permitAll().and().logout()
				.permitAll().and().csrf().disable();
	}

	@Configuration
	protected static class AuthenticationConfiguration extends
			GlobalAuthenticationConfigurerAdapter {
		/**
		 * this method where you define the roles and username and password
		 * @param auth
		 * @throws Exception
		 */
		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("admin").password("admin")
					.roles("ADMIN", "USER").and().withUser("user")
					.password("user").roles("USER");
		}
	}
}