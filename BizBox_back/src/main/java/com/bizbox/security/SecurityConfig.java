package com.bizbox.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/","/oauth2/**","/login/**","/css/**",
					"images/**","/js/**","/console/**")
			.permitAll()
			.antMatchers("/google").hasAuthority(GOOGLE.getRoleType())
	}
}
