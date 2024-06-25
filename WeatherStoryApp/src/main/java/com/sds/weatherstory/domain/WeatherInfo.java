package com.sds.weatherstory.domain;

import java.util.List;

import lombok.Data;

@Data
public class WeatherInfo {
	private String id;
	private double temp;
	private int humidity;
	
	String des_name;
	String hum_name;
	
	private int description_idx;
	private int temp_idx;
	private int humidity_idx;
	
	private List<Food> foodList;
	
	private Restaurant restaurant;
	private UserLocation userLocation;
}
