package com.example.myproject;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.myproject.service.ImageUploadService;

@SpringBootApplication
public class EcomProjectApplication //implements CommandLineRunner
{

	public static void main(String[] args) {
		SpringApplication.run(EcomProjectApplication.class, args);
	}
	
	@Resource
	ImageUploadService imageService;
	
	/*public void run(String... args) throws Exception {
		imageService.init();
	}*/

}

	
