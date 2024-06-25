package com.sds.weatherstory.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sds.weatherstory.domain.Story;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileManager {

	@Autowired
	private ResourceLoader resourceLoader;
	@Value("${file.upload}")
	private String uploadLocation;

	public void save(Story story) {
		MultipartFile photo = story.getPhoto();
		String filename = createFilename(getExt(photo.getOriginalFilename()));
		
		try {
			File directory = resourceLoader.getResource(uploadLocation).getFile();
			log.debug("파일 저장할 풀 경로 : " + directory.getAbsolutePath());
			Path path = Paths.get(directory.getAbsolutePath());
			Path savePath = path.resolve(filename);
			log.debug("스토리 savePath : " + savePath.toString());
			Files.copy(photo.getInputStream(), savePath);
			
			story.setFilename(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getExt(String path) {
		return path.substring(path.lastIndexOf(".") + 1, path.length());
	}

	public String createFilename(String ext) {
		long time = System.currentTimeMillis();
		return time + "." + ext;
	}
}
