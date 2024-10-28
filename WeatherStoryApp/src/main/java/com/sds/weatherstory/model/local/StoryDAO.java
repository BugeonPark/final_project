package com.sds.weatherstory.model.local;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.weatherstory.domain.Story;

@Mapper
public interface StoryDAO {
	public int insert(Story story);
	public List<Story> selectByMember(String dong);
	public List<Story> selectByXY(Story story);
	public List<Story> selectByPlaceName(String place_name);
	
	public Story selectByStoryIdx(int story_idx);

	public int updateHit(int story_idx); // hit(추천 수)를 1씩 증가시키는 메서드
	public int downHit(int story_idx); // hit(추천 수)를 1씩 감소시키는 메서드
	
	public int selectHit(int story_idx); // pk를 통해 hit 값 조회
}
