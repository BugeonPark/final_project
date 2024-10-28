package com.sds.weatherstory.domain;

import java.util.List;

import lombok.Data;

@Data
public class OpenWeather {
	
	
	@Data
	public static class Weather{
		private int id;
		private String main;
		private String description;
		private String icon;
	}
	
	@Data
	public static class Main{
		
	}
	
}
