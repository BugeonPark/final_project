package com.sds.weatherstory.domain;

import java.util.List;

import lombok.Data;

@Data
public class UserLocation {

	private List<Document> documents;

	@Data
	public static class Document {
		private String address_name;
		private String x;
		private String y;
	}
}
