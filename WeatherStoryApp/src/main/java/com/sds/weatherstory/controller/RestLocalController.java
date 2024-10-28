package com.sds.weatherstory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sds.weatherstory.domain.AddrDong;
import com.sds.weatherstory.domain.Member;
import com.sds.weatherstory.domain.Story;
import com.sds.weatherstory.exception.StoryException;
import com.sds.weatherstory.jwt.JwtvalidService;
import com.sds.weatherstory.model.local.LikeyService;
import com.sds.weatherstory.model.local.StoryService;
import com.sds.weatherstory.model.map.MapAPIService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestLocalController {

	@Autowired
	private MapAPIService mapAPIService;
	@Autowired
	private StoryService storyService;
	@Autowired
	private JwtvalidService jwtvalidService;
	@Autowired
	private LikeyService likeyService;

	@PostMapping("/rest/local/story")
	public ResponseEntity getStory(@RequestHeader("Authorization") String header) {

		String token = header.replace("Bearer ", "");
		Member member = jwtvalidService.getMemberFromJwt(token);

		ResponseEntity entity = ResponseEntity.ok(member);
		return entity;
	}

	@PostMapping("/local/story")
	public ResponseEntity registStory(@RequestHeader("Authorization") String header, Story story) {
//		Member member = (Member)session.getAttribute("member");
//		story.setMember(member);
		String token = header.replace("Bearer ", "");
		Member member = jwtvalidService.getMemberFromJwt(token);
		story.setMember(member);
		log.debug("등록하려고 하는 스토리 : " + story);

		AddrDong dto = mapAPIService.getDong(story);
		story.setDong(dto.getDocuments().get(0).getRegion_3depth_name());

		storyService.regist(story);

		ResponseEntity entity = ResponseEntity.ok(dto.getDocuments());
		return entity;
	}

	@GetMapping("/local/show")
	public ResponseEntity showPlaces(@RequestHeader("Authorization") String header) {
		String token = header.replace("Bearer ", "");
		Member member = jwtvalidService.getMemberFromJwt(token);
		List<Story> list = storyService.getPlaces(member);
		ResponseEntity entity = ResponseEntity.ok(list);
		return entity;

	}

	@GetMapping("/local/place")
	public ResponseEntity showList(@RequestParam(value = "place_name") String place_name) {
		List<Story> list = storyService.getStories(place_name);
		ResponseEntity entity = ResponseEntity.ok(list);
		return entity;
	}

	// 추천 기록(= Likey 레코드) 및 hit(추천 수)를 변경하는 메서드
	@PostMapping("/local/hit")
	public ResponseEntity updateHit(@RequestParam(value = "story_idx", defaultValue = "0") int story_idx,
			@RequestHeader("Authorization") String header, @RequestParam(value = "place_name") String place_name) {

		Member member = jwtvalidService.getMemberFromJwt(header.replace("Bearer", ""));

		int result = likeyService.update(story_idx, member); // 회원이 해당 스토리를 추천한 기록이 있는지 조회
		// -> 기록이 없다면 추천 수 +1, 추천 테이블에 레코드(= 추천 기록) 등록
		// -> 기록이 있다면 추천 수 -1, 추천 테이블에서 레코드(= 추천 기록) 삭제

		// List<Story> list = storyService.getStories(place_name); // 추천 수 변경이 반영된
		// Story들을 새로 호출해서 화면에 띄우기
		// ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).build();

		ResponseEntity entity = ResponseEntity.ok(result); // 변경된 추천 수를 전달

		return entity;
	}

	@ExceptionHandler(StoryException.class)
	public ResponseEntity handle(StoryException e) {
		e.printStackTrace();
		ResponseEntity entity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return entity;
	}

}
