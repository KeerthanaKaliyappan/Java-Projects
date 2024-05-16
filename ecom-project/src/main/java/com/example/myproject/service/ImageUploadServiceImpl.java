package com.example.myproject.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ImageUploadServiceImpl implements ImageUploadService{
	
	private final Path root = Paths.get("image-folder");

	@Override
	public void init() {
		try {
			Files.createDirectories(root);
		}
		catch(IOException e) {
			System.out.println("Could not initialize to create folder");
		}
	}

	@Override
	public void save(MultipartFile imageFile) {
		try {
			Files.copy(imageFile.getInputStream(),this.root.resolve(imageFile.getOriginalFilename()));
		}
		catch(IOException e) {
			System.out.println("Cannot save image"+e);
		}
	}

	@Override
	public Resource loadImage(String fileName) {
		try {
			Path imageFile = root.resolve(fileName);
			Resource resource = new UrlResource(imageFile.toUri());
			
			if(resource.exists() || resource.isReadable())
				return resource;
			else
				throw new RuntimeException("Cannot read the file!");
		}
		catch(MalformedURLException e) {
			throw new RuntimeException("Malformed Url"+e);
		}
	}

}
