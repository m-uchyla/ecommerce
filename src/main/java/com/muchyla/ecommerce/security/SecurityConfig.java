package com.muchyla.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private DefaultUserDetailsService userDetailsService;

	public SecurityConfig(DefaultUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{

		http
		.authorizeRequests()
			.antMatchers("/**/*.html","/**/*.js","/**/*.css","/**/*.png","/auth/**","/", "/resources/**","/oauth2/authorization/google").permitAll()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasRole("USER")
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/auth/login")
			.loginProcessingUrl("/process_login")
			.defaultSuccessUrl("/twoFactorAuth", true)
			.failureUrl("/auth/login?error")
			.and()
		.logout()
			.logoutUrl("/logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/auth/login")
			.and()
		.oauth2Login()
			.successHandler(successHandler())
			.and()
		.csrf()
			.disable()
		.httpBasic()
			.and()
		.headers().frameOptions().disable();
	}
	
	private AuthenticationSuccessHandler successHandler() {
		SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
			handler.setDefaultTargetUrl("/oauth2/code/google");
			handler.setUseReferer(false);
		return handler;
	}
}
