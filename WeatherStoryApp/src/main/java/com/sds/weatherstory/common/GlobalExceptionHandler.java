package com.sds.weatherstory.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import com.sds.weatherstory.exception.JwtException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(JwtException.class)
//	public ResponseEntity<Map<String, Object>> 
	public Object handle(JwtException e, HandlerMethod handlerMethod) {
		//ResponseEntity가 반환형이라는 것은 json으로 응답 보네겠다는 뜻.
		//handlerMethod는 이 예외를 일으킨 컨트롤러의 반환형을 알 수 있음.
		if(handlerMethod.getMethod().getReturnType().equals(ResponseEntity.class)) {
			log.debug( e.getMessage()+", 글로벌 예외처리");
			
			//자바의 객체는 json으로 변경되어 반환이 됨.
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("msg", e.getMessage()); //에러 메시지 
			map.put("status", HttpStatus.UNAUTHORIZED.value()); //상태 코드
			map.put("error", HttpStatus.UNAUTHORIZED.getReasonPhrase());//상태 코드에 대한 문자열
			
			return new ResponseEntity(map, HttpStatus.UNAUTHORIZED);
		}else {
			return "error/result";
		}
	}
}
