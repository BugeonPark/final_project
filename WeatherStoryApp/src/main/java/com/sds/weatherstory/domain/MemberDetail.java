package com.sds.weatherstory.domain;

import lombok.Data;

@Data
public class MemberDetail {
	private int member_detail_idx;
	private String password;
	
	private String roadAddress;
	private String sido;
	private String sidoEnglish;
	private String dong;
	
	private Member member;
	
	private Food favoriteFood;
	private Food hateFood;
}
