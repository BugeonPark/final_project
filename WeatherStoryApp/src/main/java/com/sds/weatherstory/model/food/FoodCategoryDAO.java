package com.sds.weatherstory.model.food;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.weatherstory.domain.FoodCategory;

@Mapper
public interface FoodCategoryDAO {
	public List<FoodCategory> selectAll();
	public FoodCategory selectByIdx(int food_category_idx);
}
