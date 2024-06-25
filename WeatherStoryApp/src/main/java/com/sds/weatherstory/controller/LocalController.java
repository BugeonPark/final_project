package com.sds.weatherstory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocalController {
	
	@GetMapping("/local")
	public String getLocal() {
		
		return "local/story_index";
	}
	
	@GetMapping("/local/story")
	public String getStoryForm() {
		return "/local/story";
	}
	
	
	
}
