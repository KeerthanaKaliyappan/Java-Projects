package com.example.myproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long productid;
	String productname;
	String productdescription;
	Double price;
	String imagePath;
	
	public Long getProductid() {
		return productid;
	}
	public void setProductid(Long productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getProductdescription() {
		return productdescription;
	}
	public void setProductdescription(String productdescription) {
		this.productdescription = productdescription;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public Product() {
		super();
	}
	
	public Product(Long productid, String productname, String productdescription, Double price, String imagePath) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.productdescription = productdescription;
		this.price = price;
		this.imagePath = imagePath;
	}
	
	@Override
	public String toString() {
		return "Product [productid=" + productid + ", productname=" + productname + ", productdescription="
				+ productdescription + ", price=" + price + ", imagePath=" + imagePath + "]";
	}
	
}
