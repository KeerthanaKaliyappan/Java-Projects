package com.example.myproject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.myproject.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
	
	User findByEmail(String email);
	
	@Query(value="SELECT count(u.email) FROM User u WHERE u.email = :email")
	Integer findExistingUser(String email);

}
