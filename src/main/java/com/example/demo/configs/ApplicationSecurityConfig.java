package com.example.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/","index","/css/*","/js/*")	
			.permitAll()
			.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
				/*
				 * .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(
				 * ApplicationUserPermission.COURSE_WRITE.getPermission())
				 * .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(
				 * ApplicationUserPermission.COURSE_WRITE.getPermission())
				 * .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(
				 * ApplicationUserPermission.COURSE_WRITE.getPermission())
				 * .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(
				 * ApplicationUserRole.ADMIN.name(),ApplicationUserRole.ADMINTRAINEE.name())
				 */
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails vishalUser = User.builder()
		.username("vishal")
		.password(passwordEncoder.encode("password"))
				/* .roles(ApplicationUserRole.STUDENT.name()) */
		.authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
		.build();	
		
		UserDetails onjoUser = User.builder()
		.username("Nam-Onjo")
		.password(passwordEncoder.encode("password"))
				/* .roles(ApplicationUserRole.ADMIN.name()) */
		.authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
		.build();
		
		UserDetails namraUser = User.builder()
				.username("Nam-Ra")
				.password(passwordEncoder.encode("password"))
				/* .roles(ApplicationUserRole.ADMINTRAINEE.name()) */
				.authorities(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities())
				.build();
		
		return new InMemoryUserDetailsManager(vishalUser,onjoUser,namraUser);
	}
	
	
}
