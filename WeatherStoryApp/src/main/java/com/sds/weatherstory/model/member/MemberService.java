package com.sds.weatherstory.model.member;

import com.sds.weatherstory.domain.Member;
import com.sds.weatherstory.domain.MemberUpdate;

public interface MemberService {
	
	public void regist(Member member);
	public Member login(Member member);
	public Member selectByUid(String uid);
	public Member update(Member member, MemberUpdate memberUpdate);
	public Member selectAllByUid(String uid);
}
