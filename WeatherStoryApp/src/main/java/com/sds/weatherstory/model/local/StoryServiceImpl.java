package com.sds.weatherstory.model.local;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sds.weatherstory.common.FileManager;
import com.sds.weatherstory.domain.Member;
import com.sds.weatherstory.domain.Story;
import com.sds.weatherstory.exception.StoryException;

@Service
public class StoryServiceImpl implements StoryService{
	
	@Autowired
	private FileManager fileManager;
	@Autowired
	private StoryDAO storyDAO;
	
	@Transactional
	public void regist(Story story) throws StoryException{
		int result = 0;
		fileManager.save(story);
		result = storyDAO.insert(story);
		if(result<1) {
			throw new StoryException("스토리 등록 실패");
		}
	}
	
	public List<Story> getPlaces(Member member){
		String dong = member.getMemberDetail().getDong();
		List<Story> list = storyDAO.selectByMember(dong);
		return list;
	}

	@Override
	public List<Story> getStories(String place_name) {
		List<Story> list = storyDAO.selectByPlaceName(place_name);
		return list;
	}
	
	public void updateHit(int story_idx) {
		int result = 0;
		result = storyDAO.updateHit(story_idx); // 스토리 테이블의 추천 수 +1 하기
		if(result < 1) {
			throw new StoryException("스토리 추천 수 증가 실패");
		}
	}

	// hit(추천 수)를 1씩 감소시키는 메서드
	public void downHit(int story_idx) {
		int result = 0;
		result = storyDAO.downHit(story_idx); // 스토리 테이블의 추천 수 -1 하기
		if(result < 1) {
			throw new StoryException("스토리 추천 수 감소 실패");
		}
	}
}
