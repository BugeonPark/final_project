package com.sds.weatherstory.domain;

import java.util.List;

import lombok.Data;

@Data
public class Restaurant {
	
	private List<Document> documents;
	private Urls urls;
	
	@Data
	static class Document {
		private String address_name;
		private String category_group_code;
		private String category_group_name;
		private String category_name;
		private String distance;
		private String id;
		private String phone;
		private String place_name;
		private String place_url;
		private String road_address_name;
		private String x;
		private String y;
	}
}
