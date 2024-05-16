package com.example.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myproject.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	Payment findByCardNumber(String cardNumber);
}
