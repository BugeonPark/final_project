package com.sds.weatherstory.model.food;

import org.apache.ibatis.annotations.Mapper;

import com.sds.weatherstory.domain.FoodTaste;

@Mapper
public interface FoodTasteDAO {
	public int insert(FoodTaste foodTaste);
	
	public FoodTaste selectByFoodIdx(int food_idx);
}
