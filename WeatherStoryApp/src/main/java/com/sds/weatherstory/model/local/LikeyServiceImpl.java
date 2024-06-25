package com.sds.weatherstory.model.local;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sds.weatherstory.domain.Likey;
import com.sds.weatherstory.domain.Member;
import com.sds.weatherstory.domain.Story;
import com.sds.weatherstory.exception.LikeyException;
import com.sds.weatherstory.model.member.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LikeyServiceImpl implements LikeyService{
	@Autowired
    private LikeyDAO likeyDAO;
    @Autowired
    private StoryDAO storyDAO;
    @Autowired
    private MemberDAO memberDAO;


    @Transactional
    public int update(int story_idx, Member member) {
        Story story = storyDAO.selectByStoryIdx(story_idx);
        // Member member = memberDAO.selectByMemberIdx(member_idx);

        Likey likey = new Likey();
       likey.setStory(story);
        likey.setMember(member);

        Likey likeResult = likeyDAO.select(likey); // 추천 기록이 있는지 조회

        if(likeResult == null) { // 해당 스토리를 추천한 적이 없음
            int result = 0;

            result = likeyDAO.insert(likey); // 누가, 어떤 스토리를 추천했는지 등록

            if(result < 1) {
                throw new LikeyException("추천 수 등록 실패");
            }

            storyDAO.updateHit(likey.getStory().getStory_idx()); // story 테이블에서 해당 스토리의 hit(추천 수)를 1 증가

        } else { // 해당 스토리를 추천한 적 있음
            int result = 0;

            result = likeyDAO.delete(likey); // 추천 기록 삭제

            if(result < 1) {
                throw new LikeyException("추천 기록 삭제 실패");
            }

            storyDAO.downHit(likey.getStory().getStory_idx());  // story 테이블에서 해당 스토리의 hit(추천 수)를 1 감소
        }

       return storyDAO.selectHit(likey.getStory().getStory_idx());
    }

}
