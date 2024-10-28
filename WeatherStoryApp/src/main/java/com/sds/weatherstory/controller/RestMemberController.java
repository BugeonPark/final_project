package com.sds.weatherstory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sds.weatherstory.domain.Member;
import com.sds.weatherstory.jwt.JwtvalidService;
import com.sds.weatherstory.model.member.MemberService;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class RestMemberController {
	
	@Autowired
	private JwtvalidService jwtvalidService;
	@Autowired
	private MemberService memberService;
	//로그인 요청에 필요한 링크 주소 및 파라미터 생성 요청 처리
	
	@GetMapping("/rest/member/logincheck")
	public ResponseEntity getLoginMember(@RequestHeader("Authorization") String header) {
		
		log.debug("토큰 검증 요청"+header);
		//넘어온 헤더값은 "Bearer 토큰값"인데, 순수 토큰만을 추출
		String token = header.replace("Bearer ", "");
		
		Member member = jwtvalidService.getMemberFromJwt(token);
		
		ResponseEntity entity = ResponseEntity.ok(member);
		
		return entity;
	}
	
}
