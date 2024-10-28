package com.sds.weatherstory.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Story {
	private int story_idx;
	
	private Member member;
	
	private String content;
	
	private MultipartFile photo;
	private String filename;
	
	private String place_name;
	private String roadAddress;
	private String dong;
	private String x;
	private String y;
	
	private int hit;
}
