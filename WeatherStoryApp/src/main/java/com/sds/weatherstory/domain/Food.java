package com.sds.weatherstory.domain;

import java.util.List;

import lombok.Data;

@Data
public class Food {
	private int food_idx;
	private String food_name;
	private List<FoodDescription> descriptions;
	private List<FoodHumidity> humidities;
	private List<FoodTemp> temps;
	private List<FoodTaste> tastes;
	private FoodCategory foodCategory; 
}
