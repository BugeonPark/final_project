package com.sds.weatherstory.model.map;

import com.sds.weatherstory.domain.AddrDong;
import com.sds.weatherstory.domain.MemberDetail;
import com.sds.weatherstory.domain.Restaurant;
import com.sds.weatherstory.domain.Story;
import com.sds.weatherstory.domain.UserLocation;

public interface MapAPIService {
	
	public Restaurant getResaurant(String x, String y, String food_name);
	public UserLocation getUserLocation(MemberDetail memberDetail);
	public AddrDong getDong(Story story);
}
