package com.sds.weatherstory.model.member;

import org.apache.ibatis.annotations.Mapper;

import com.sds.weatherstory.domain.Member;
import com.sds.weatherstory.domain.MemberUpdate;

@Mapper
public interface MemberDAO {
	public int insert(Member member);
	public Member login(Member member);
	public Member selectByUid(String uid);
	public Member selectByMemberIdx(int member_idx);
	public Member selectAllByUid(String uid);
	public int updateNickname(MemberUpdate memberUpdate);
}
