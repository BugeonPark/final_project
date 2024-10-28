package com.sds.weatherstory.jwt;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeyService {
	public String getPublicKey() {
		
		HttpHeaders headers = new HttpHeaders();	//movieapp와 통신하기 위해서는 http 통신을 해야 하기 때문에 header을 만들어야 햠. 요청만 하기 때문에 body는 필요 없음
		HttpEntity entity = new HttpEntity(headers);
		
		//비동기 GET 요청
		String url="http://localhost:9898/jwt/key"; //movieapp, gateway를 통해 movieapp에 접속해야 하므로 외부에 접속하는 것이다. 따라서 http:// 가 필요함
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		log.debug("movieapp로부터 가져온 공개키 : " + response.getBody());
		return response.getBody();
	}
}
