package com.sds.weatherstory.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.weatherstory.domain.Member;
import com.sds.weatherstory.exception.JwtException;
import com.sds.weatherstory.model.member.MemberService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtvalidService {
	@Autowired
	private MemberService memberService;
	@Autowired
	private KeyService keyService;
	@Autowired
	private JwtUtil jwtUtil;
	
	public Member getMemberFromJwt(String token) {
		
		String publicKey = keyService.getPublicKey();
		
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(jwtUtil.getPublicKeyFromString(publicKey)).parseClaimsJws(token).getBody();
			
		} catch (Exception e) {
			log.debug("JWT 인증 실패");
			throw new JwtException("Jwt 인증 실패");
		}
		String uid = claims.getSubject();
		Member member = memberService.selectAllByUid(uid);
		return member;
	}
}
