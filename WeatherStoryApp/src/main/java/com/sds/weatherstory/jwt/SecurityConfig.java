package com.sds.weatherstory.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager(); 
	}
	private JwtUtil jwtUtil;
	public SecurityConfig(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeHttpRequests(
				(auth) -> auth
				.requestMatchers("/site/**", "/admin/**", "/").permitAll()
				.requestMatchers("/story_img/**").permitAll()
				.requestMatchers("/member/loginform","/member/login", "/member/regist", "/member/registform").permitAll()
				.requestMatchers("/member/update").permitAll()
				.requestMatchers("/jwt/key").permitAll()
				
				.requestMatchers("food/**").permitAll()
				.requestMatchers("rest/food/prefer").permitAll()
				
				.requestMatchers("/local").permitAll()
				.requestMatchers("/local/story").permitAll()
				.requestMatchers("/local/show").permitAll()
				.requestMatchers("/local/place").permitAll()
				.requestMatchers("/local/hit").permitAll()
				.requestMatchers("/rest/local/story").permitAll()
				
				.requestMatchers("/rest/show").permitAll()
				.requestMatchers("/rest/search").permitAll()
				
				.requestMatchers("/rest/member/logincheck").permitAll()
				.requestMatchers("/rest/member/authform/**").permitAll()
				
				.requestMatchers("/member/sns/naver/callback").permitAll()
				.requestMatchers("/member/sns/kakao/callback").permitAll()
				.anyRequest().authenticated()
				);

		httpSecurity.formLogin((auth) -> auth.disable());
		LoginFilter customFilter = new LoginFilter(authenticationManager(), jwtUtil);
		customFilter.setFilterProcessesUrl("/member/login");
		httpSecurity.addFilterAt(customFilter, UsernamePasswordAuthenticationFilter.class);
//		httpSecurity
//		.formLogin((auth)->
//			auth.loginPage("/member/loginform")
//			.successHandler(loginEventHandler())  
//			.failureHandler(loginFailHandler())
//			.loginProcessingUrl("/member/login")		
//			.usernameParameter("uid")		
//			.passwordParameter("password")
//		);
//		
//		httpSecurity
//		.logout(logout -> logout
//				.logoutUrl("/member/logout")
//				.logoutSuccessUrl("/")
//				.invalidateHttpSession(true)
//				.clearAuthentication(true)
//				.deleteCookies("JSESSIONID")
//				);
		
		httpSecurity.csrf((auth)->auth.disable());	
		return httpSecurity.build();
	}
	
//	@Bean 
//	public AuthenticationSuccessHandler loginEventHandler() {
//		return new LoginEventHandler();
//	}
//	@Bean
//	public AuthenticationFailureHandler loginFailHandler() {
//		return new LoginFailHandler();
//	}
}

