package com.sds.weatherstory.model.food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.weatherstory.domain.Food;
import com.sds.weatherstory.domain.FoodCategory;
import com.sds.weatherstory.domain.FoodDescription;
import com.sds.weatherstory.domain.FoodHumidity;
import com.sds.weatherstory.domain.FoodTaste;
import com.sds.weatherstory.domain.FoodTemp;
import com.sds.weatherstory.domain.Member;
import com.sds.weatherstory.exception.FoodException;

@Service
public class FoodServiceImpl implements FoodService{
	
	@Autowired
	private FoodDAO foodDAO;
	@Autowired
	private FoodDescriptionDAO foodDescriptionDAO;
	@Autowired
	private FoodHumidityDAO foodHumidityDAO;
	@Autowired
	private FoodTempDAO foodTempDAO;
	@Autowired
	private FoodTasteDAO foodTasteDAO;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Food food) throws FoodException{
		
		int result = foodDAO.insert(food);
		if(result<1) {
			throw new FoodException("음식 등록 실패");
		}
		
		for(FoodDescription fd : food.getDescriptions()) {
			fd.setFood(food);
			result = foodDescriptionDAO.insert(fd);
			if(result < 1) {
				throw new FoodException("날씨 정보 등록 실패");
			}
		}
		for(FoodHumidity fh : food.getHumidities()) {
			fh.setFood(food);
			result = foodHumidityDAO.insert(fh);
			if(result<1) {
				throw new FoodException("습도 정보 등록 실패");
			}
		}
		for(FoodTemp ft : food.getTemps()) {
			ft.setFood(food);
			result = foodTempDAO.insert(ft);
			if(result < 1) {
				throw new FoodException("온도 정보 등록 실패");
			}
		}
		for(FoodTaste ftaste : food.getTastes()) {
			ftaste.setFood(food);
			result = foodTasteDAO.insert(ftaste);
			if(result<1) {
				throw new FoodException("맛 정보 등록 실패");
			}
		}
		
	}
	
	@Override
	public List selectAll() {
		return foodDAO.selectAll();
	}
	
	@Override
	public List selectByWeather(Member member, Map map, Food favorite) {
		List<Food> foodList = foodDAO.selectByWeather(map); 
		
		Map<Integer, Double> scoreMap = new HashMap();
		for(Food food : foodList) {
			double score = getScore(favorite, food);
			scoreMap.put(food.getFood_idx(), score);
		}
		
		
		List<Food> result = new ArrayList();
		result = scoreMap.entrySet().stream()
				.sorted(Map.Entry.<Integer, Double> comparingByValue().reversed())
				.map(e -> foodDAO.selectByFoodIdx(e.getKey()))
				.collect(Collectors.toList());
		return result;
	}
	
	public double getScore(Food f1, Food f2) {
		double score = 0.0;
		FoodCategory foodCategory1 = f1.getFoodCategory();
		FoodCategory foodCategory2 = f2.getFoodCategory();
		if(foodCategory1.getFood_category_idx() == foodCategory2.getFood_category_idx())
			score += 3.0;
		
		List<FoodTaste> tastes1 = f1.getTastes();
		List<FoodTaste> tastes2 = f2.getTastes();
		for(FoodTaste ft1 : tastes1) {
			for(FoodTaste ft2 : tastes2) {
				if(ft1.getTaste().getTaste_idx() == ft2.getTaste().getTaste_idx()) {
					score += 0.7;
				}
			}
		}
		
		return score;
	}
}
