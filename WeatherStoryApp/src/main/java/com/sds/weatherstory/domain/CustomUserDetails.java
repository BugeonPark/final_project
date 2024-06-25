package com.sds.weatherstory.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails{
	
	private Member member;
	
	public CustomUserDetails(Member member){
		this.member = member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return member.getRole().getRole_name();
			}
		});
				
		return authList;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return member.getMemberDetail().getPassword();
	}

	@Override
	public String getUsername() {
		return member.getNickname();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}
