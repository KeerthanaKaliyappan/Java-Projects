package com.example.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.entity.User;
import com.example.myproject.repository.UserRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("ecom/userController")
public class UserController {

	@Autowired
	UserRepository userRepo;
	
	//Register new user account
	@PostMapping("register")
	public User registerUser(@RequestBody User user) {
		Integer userCount = 0;
		userCount = userRepo.findExistingUser(user.getEmail());
		if(userCount >= 1)
			return null;
		else
			return userRepo.save(user);
	}
		
	//Login existing user
	@GetMapping("login/{email}")
	public User loginUser(@PathVariable(name="email") String email) {
		return userRepo.findByEmail(email);
	}
}
