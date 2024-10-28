package com.sds.weatherstory.model.local;

import java.util.List;

import com.sds.weatherstory.domain.Member;
import com.sds.weatherstory.domain.Story;

public interface StoryService {
	public void regist(Story story);
	public List<Story> getPlaces(Member member);
	public List<Story> getStories(String place_name);
	
	public void updateHit(int story_idx); // hit(추천 수)를 1씩 증가시키는 메서드
	public void downHit(int story_idx); // hit(추천 수)를 1씩 감소시키는 메서드
}
