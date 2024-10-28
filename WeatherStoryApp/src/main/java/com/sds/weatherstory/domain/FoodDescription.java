package com.sds.weatherstory.domain;

import lombok.Data;

@Data
public class FoodDescription {
	private int food_description_idx;
	private Food food;
	private Description description;
}
