package com.sds.weatherstory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication(scanBasePackages = "com.sds.weatherstory")
@EnableDiscoveryClient
public class WeatherStoryAppApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WeatherStoryAppApplication.class);
	}	

	public static void main(String[] args) {
		SpringApplication.run(WeatherStoryAppApplication.class, args);
	}

}
