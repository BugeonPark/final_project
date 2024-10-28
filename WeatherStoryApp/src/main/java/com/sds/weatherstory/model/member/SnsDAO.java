package com.sds.weatherstory.model.member;

import org.apache.ibatis.annotations.Mapper;

import com.sds.weatherstory.domain.Sns;

@Mapper
public interface SnsDAO {
	public Sns selectByName(String sns_name);
	public Sns select(int sns_idx);
}
