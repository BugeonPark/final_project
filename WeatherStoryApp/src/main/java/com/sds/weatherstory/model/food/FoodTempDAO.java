package com.sds.weatherstory.model.food;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.weatherstory.domain.FoodTemp;

@Mapper
public interface FoodTempDAO {
	public int insert(FoodTemp foodTemp);
	
	public List<FoodTemp> selectByFoodIdx(int food_idx);
}
