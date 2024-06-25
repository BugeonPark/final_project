package com.sds.weatherstory.model.member;

import org.apache.ibatis.annotations.Mapper;

import com.sds.weatherstory.domain.MemberUpdate;

@Mapper
public interface MemberUpdateDAO {
	public int insertIfNull(MemberUpdate memberUpdate);
	public int updateSns(MemberUpdate memberUpdate);
	public int updateHomepage(MemberUpdate memberUpdate);
	public int updateNickname(MemberUpdate memberUpdate);
}
