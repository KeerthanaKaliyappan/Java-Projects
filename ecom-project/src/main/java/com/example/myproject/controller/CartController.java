package com.example.myproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.entity.Cart;
import com.example.myproject.repository.CartRepository;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("ecom/cartController")
public class CartController {
	
	@Autowired
	CartRepository cartRepo;
	
	//Checks for already existing product in user's cart
	@GetMapping("checkCart/{userId}/{productId}")
	public Boolean checkForUniqueCartProducts(@PathVariable(name ="userId") int userId, @PathVariable(name="productId") long productId) {
		if(cartRepo.existingCartItem(userId, productId) < 1)
			return true;
		return false;
	}
		
	//Adds product to cart
	@PostMapping("addCart")
	public Cart addToCart(@RequestBody Cart cart) {
		return cartRepo.save(cart);
	}
		
	//Gets single user's Cart value
	@GetMapping("cart/{userId}")
	public List<Cart> listCartDetails(@PathVariable(name="userId") int userId){
		if(cartRepo.minCartValue(userId) > 0)
			return cartRepo.findByUserId(userId);
		else 
			return null;
	}
		
	//Get Cart's total price
	@GetMapping("cartPrice/{userId}")
	public Double totalCartPrice(@PathVariable(name="userId") int userId) {
		if(cartRepo.minCartValue(userId) > 0) 
			return cartRepo.getTotalCartPrice(userId);
		else
			return null;
	}
		
	//Update Cart item
	@PutMapping("cart/{userId}/{productid}")
	public Cart updateCartItem(
			@PathVariable("userId") int userId,
			@PathVariable("productid") long productid,
			@RequestBody Cart cart) {
		Cart updatedCart = cartRepo.findCartItemByUserIdAndProductId(userId, productid);
			
		updatedCart.setUserId(cart.getUserId());
		updatedCart.setProductId(cart.getProductId());
		updatedCart.setQuantity(cart.getQuantity());
		updatedCart.setSubprice(cart.getSubprice());
			
		return cartRepo.save(updatedCart);
	}
		
	//Deletes single Cart item
	@DeleteMapping("cart/{userId}/{productid}")
	public Map<String, Boolean> deleteCartItem(
			@PathVariable(name="userId") int userId, 
			@PathVariable(name="productid") long productid) {
		Cart cart = cartRepo.findCartItemByUserIdAndProductId(userId, productid);
		cartRepo.delete(cart);
			
		Map<String, Boolean> responseValue = new HashMap<>();
		responseValue.put("product deleted", Boolean.TRUE);
		return responseValue;
	}
	
	//Deletes all cart item
	@DeleteMapping("cart/{userId}")
	public Map<String, Boolean> deleteCart(@PathVariable("userId") int userId){
		
		List<Long> cartIdList = cartRepo.findByCartIdByUserId(userId);

		for(Long i : cartIdList) 
			cartRepo.deleteById(i);
			
		Map<String, Boolean> responseValue = new HashMap<>();
		responseValue.put("cart deleted",Boolean.TRUE);
		return responseValue;
	}

}
