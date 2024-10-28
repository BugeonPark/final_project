package com.sds.weatherstory.domain;

import java.util.List;

import lombok.Data;

@Data
public class MemberFoodCategory {
	private Member member;
	private List<FoodCategory> categoryList;
}
