package com.sds.weatherstory.model.food;

import org.apache.ibatis.annotations.Mapper;

import com.sds.weatherstory.domain.FoodDescription;

@Mapper
public interface FoodDescriptionDAO {
	public int insert(FoodDescription foodDescription);
}
