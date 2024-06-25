package com.sds.weatherstory.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sds.weatherstory.domain.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter{
	
	private JwtUtil jwtUtil;		//필터이기 때문에 인스턴스 등록 이전에 뜸. autowired 불가
	
	private AuthenticationManager authenticationManager;
	
	public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) throws Exception {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	//로그인 시도시
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		String username = this.obtainUsername(request);
		String password = this.obtainPassword(request);
		
		log.debug("username ================================ " + username);
		log.debug("password ================================ " + password);
		
		Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(auth);
	}
	
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter("uid");
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		
		log.debug("로그인 성공 회원 정보 존재");
		
		CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();
		String username = customUserDetails.getMember().getUid();
		String role = customUserDetails.getMember().getRole().getRole_name();
		
		long expireTime = ((1 * 1000) * 60) * 30;
		String token = null;
		log.debug("빈으로 등록된 jwtUtil : " + jwtUtil);
		token = jwtUtil.generateToken(username, role, expireTime);
		
		request.getServletContext().setAttribute("key", jwtUtil.getEncodedPublicKey());
		
		Map<String, Object> responseMap = new HashMap();
		responseMap.put("success", true);
		responseMap.put("token", token);
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), responseMap);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		log.debug("회원 정보 존재하지 않음");
	}
}
