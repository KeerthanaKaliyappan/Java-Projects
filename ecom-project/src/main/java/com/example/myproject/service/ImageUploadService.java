package com.example.myproject.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
	
	public void init();
	
	public void save(MultipartFile file);
	
	public Resource loadImage(String fileName);

}
