package com.sds.weatherstory.model.food;

import org.apache.ibatis.annotations.Mapper;

import com.sds.weatherstory.domain.FoodHumidity;

@Mapper
public interface FoodHumidityDAO {
	public int insert(FoodHumidity foodHumidity);
}
