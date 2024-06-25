package com.sds.weatherstory.model.map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sds.weatherstory.domain.AddrDong;
import com.sds.weatherstory.domain.MemberDetail;
import com.sds.weatherstory.domain.Restaurant;
import com.sds.weatherstory.domain.Story;
import com.sds.weatherstory.domain.UserLocation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MapAPIServiceImpl implements MapAPIService {

	@Value("${spring.map.api.key}")
	private String key;

	public Restaurant getResaurant(String x, String y, String foodName) { // final로 불변하도록

		final HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK " + key);

		String query = foodName + " 맛집";
		System.out.println(query);
//		String quert2 = member.getMemberDetail().getRoadAddress() + weatherInfo.getFoodList().get(index);

		String apiURL = "https://dapi.kakao.com/v2/local/search/keyword.JSON?" + "query=" + query + "&x=" + x + "&y="
				+ y + "&radius=" + "10000" + "&size=" + "15" + "&sort=" + "distance"; // config 통해서 받기. 카카오에 요청할 url은
																						// 변경사항의 여지가 있음. 카카오가 Url을 바꾼다면?

		RestTemplate restTemplate = new RestTemplate(); // restTeamplate 빈으로 등록해서 싱글톤 유지하기
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<Restaurant> response = restTemplate.exchange(apiURL, HttpMethod.GET, entity, Restaurant.class);
		log.debug("api서비스에서 카카오로부터 받아온 정보 : " + response.getBody());
		return response.getBody();
	}

	public UserLocation getUserLocation(MemberDetail memberDetail) {
		final HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK " + key);
		String address = memberDetail.getRoadAddress();
		RestTemplate restTemplate = new RestTemplate();
		String apiURL = "https://dapi.kakao.com/v2/local/search/address.JSON?" + "query=" + address;
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange(apiURL, HttpMethod.GET, entity, UserLocation.class).getBody();
	}

//	public AddrDong searchDong(Story story) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Authorization", "KakaoAK " + key);
//		
//		String apiURL = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?" + "x=" + story.getX() + "&y="
//				+ story.getY();
//		RestTemplate restTemplate = new RestTemplate();
//		
//		HttpEntity<String> entity = new HttpEntity<String>(headers);
//	}
	
	public AddrDong getDong(Story story) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK " + key);
		String apiURL = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?"
				+ "x=" + story.getX() 
				+ "&y="+ story.getY();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange(apiURL, HttpMethod.GET, entity, AddrDong.class).getBody();
	}


}
