package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("#{new Boolean('${global.isDebug:false}')}")
	private boolean isDebug;

	@Value("#{new String(${admin.httpLoginname})}")
	private String loginname;

	@Value("#{new String(${admin.httpPassword})}")
	private String password;

	private static String ROLE = "ADMIN";

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(loginname).password(password).roles(ROLE);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (isDebug) {
			http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
					.authorizeRequests().anyRequest().permitAll().and().logout().permitAll();
		} else {
			 http.csrf().disable().httpBasic().and().authorizeRequests().anyRequest().authenticated();
		}
	}
}
