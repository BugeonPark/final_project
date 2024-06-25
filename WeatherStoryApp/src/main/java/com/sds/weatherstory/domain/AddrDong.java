package com.sds.weatherstory.domain;

import java.util.List;

import com.sds.weatherstory.domain.UserLocation.Document;

import lombok.Data;

@Data
public class AddrDong {
	
	private List<Document> documents;
	
	@Data
	public static class Document{
		private String region_type;
		private String address_name;
		private String region_1depth_name;
		private String region_2depth_name;
		private String region_3depth_name;
		private String region_4depth_name;
		private String code;
		private String x;
		private String y;
	}
}
