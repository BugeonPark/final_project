package com.sds.weatherstory.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sds.weatherstory.domain.CustomUserDetails;
import com.sds.weatherstory.domain.Member;
import com.sds.weatherstory.exception.MemberException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
		
		Member member = memberDAO.selectByUid(uid);
		log.debug("시큐리티의 멤버 로그인 결과 " + member);
		if(member==null) {
			throw new MemberException("일치하는 회원 정보 존재하지 않음");
		}
//		log.debug("시큐리티 전용 dto 멤버 로그인 결과 : " + new CustomUserDetails(member));
		CustomUserDetails customUserDetails = new CustomUserDetails(member);
		log.debug("시큐리티 dto 멤버 담은 결과 : " + customUserDetails);
		
		return customUserDetails;
	}
	

}
