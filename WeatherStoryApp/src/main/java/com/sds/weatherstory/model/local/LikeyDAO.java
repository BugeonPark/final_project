package com.sds.weatherstory.model.local;

import org.apache.ibatis.annotations.Mapper;

import com.sds.weatherstory.domain.Likey;

@Mapper
public interface LikeyDAO {
	public int insert(Likey likey); // 누가, 어떤 스토리를 추천했는지 기록
    public Likey select(Likey likey); // 추천 기록이 있는지 조회
    public int delete(Likey likey); // 추천 기록 삭제
}
