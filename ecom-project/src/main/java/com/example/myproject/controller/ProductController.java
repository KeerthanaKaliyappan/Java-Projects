package com.example.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.entity.Product;
import com.example.myproject.repository.ProductRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("ecom/productController")
public class ProductController {
	
	@Autowired
	ProductRepository productRepo;
	
	//Register new products
	@PostMapping("product")
	public Product registerProduct(@RequestBody Product product) {
		return productRepo.save(product);
	}
		
	//Displays list of all available products
	@GetMapping("products")
	public List<Product> listAllProducts(){
		return productRepo.findAll();
	}
		
	//Search product by keyword
	@GetMapping("products/{keyword}")
	public List<Product> searchProduct(@PathVariable("keyword") String keyword ){
		return productRepo.findByProductname(keyword);
	}
		
	//Displays individual product details 
	@GetMapping("product/{productid}")
	public Product listProductDetails(@PathVariable(name="productid") long productid) {
		return productRepo.findByProductid(productid);
	}
		
	

}
