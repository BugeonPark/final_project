package com.sds.weatherstory.model.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.weatherstory.domain.FoodCategory;
import com.sds.weatherstory.domain.Taste;

@Service
public class FoodTypeServiceImpl implements FoodTypeService{

	@Autowired
	private TasteDAO tasteDAO;
	@Autowired
	private FoodCategoryDAO foodCategoryDAO;
	
	@Override
	public List<Taste> getTaste() {
		return tasteDAO.selectAll();
	}
	@Override
	public List<FoodCategory> getCategory() {
		return foodCategoryDAO.selectAll();
	}
}
