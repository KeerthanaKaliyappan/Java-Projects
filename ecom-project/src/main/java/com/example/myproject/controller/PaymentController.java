package com.example.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.entity.Payment;
import com.example.myproject.repository.PaymentRepository;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("ecom/paymentController")
public class PaymentController {

	@Autowired
	PaymentRepository paymentRepo;
	
	//Validate card details for payment 
	@GetMapping("pay/{cardNumber}")
	public Payment getCardDetails(@PathVariable(name="cardNumber") String cardNumber) {
		return paymentRepo.findByCardNumber(cardNumber);
	}
}
