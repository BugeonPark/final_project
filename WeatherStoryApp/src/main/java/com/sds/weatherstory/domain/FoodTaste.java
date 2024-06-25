package com.sds.weatherstory.domain;

import lombok.Data;

@Data
public class FoodTaste {
	private int food_taste_idx;
	private Food food;
	private Taste taste;
}
