package com.example.myproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.myproject.entity.Product;
import com.example.myproject.repository.ProductRepository;
import com.example.myproject.service.ImageUploadService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("ecom/adminController")
public class AdminController {
	
	List<String> files = new ArrayList<>();
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	ImageUploadService imageService;
	
	//Register new products
	@PostMapping("product")
	public Product registerProduct(@RequestBody Product product) {
		return productRepo.save(product);
	}
	
	//Saves product image file in local folder
	@PostMapping("image")
	public ResponseEntity<String> productImageUpload(@RequestParam("file") MultipartFile file) {
		String message="";
		try {
			imageService.save(file);
			files.add(file.getOriginalFilename());
			message = "Image Uploaded Successfully"+ file.getOriginalFilename()+"!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		catch(Exception e) {
			message = "Image upload Failed"+file.getOriginalFilename()+"!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
	//Displays product image
	@GetMapping("files/{fileName:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable("fileName") String fileName){
		Resource file = imageService.loadImage(fileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachments; fileName=\""+file.getFilename()+"\"")
				.body(file);
	}
	
}
