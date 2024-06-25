package com.sds.weatherstory.model.food;

import java.util.List;

import com.sds.weatherstory.domain.FoodCategory;
import com.sds.weatherstory.domain.Taste;

public interface FoodTypeService {
	public List<Taste> getTaste();
	public List<FoodCategory> getCategory();
}
