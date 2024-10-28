package com.sds.weathergateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class WeatherGatewayServerApplication {
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()

				// "/xxxxx" 프리픽스를 제거
				.route("weather_app",
						r -> r.path("/weatherapp/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:9898"))
				.route("weather_story_app",
						r -> r.path("/weatherstory/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:9797"))
				.build();
//				.route("weather_app",
//						r -> r.path("/weatherapp/**").filters(f -> f.stripPrefix(1)).uri("http://:9898"))
//				.route("story_app",
//						r -> r.path("/story/**").filters(f -> f.stripPrefix(1)).uri("http://:9797"))
//				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(WeatherGatewayServerApplication.class, args);
	}

}
