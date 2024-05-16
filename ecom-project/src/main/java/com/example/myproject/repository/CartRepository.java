package com.example.myproject.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.myproject.entity.Cart;

@Transactional
@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
	
	List<Cart> findByUserId(int userId);
	
	@Modifying
	@Query(value = "DELETE FROM Cart as c WHERE c.cartId = :cartId")
	void deleteByCartId(long cartId);
	
	@Query(value = "SELECT c.cartId FROM Cart as c WHERE c.userId = :userId")
	List<Long> findByCartIdByUserId(int userId);
	
	@Query(value = "SELECT c FROM Cart c INNER JOIN Product p ON (c.productId = p.productid) WHERE c.userId = :userId AND p.productid = :productid")
	Cart findCartItemByUserIdAndProductId(int userId, long productid);

	@Query(value = "SELECT sum(c.subprice) FROM Cart as c WHERE c.userId = :userId")
	Double getTotalCartPrice(int userId);
	
	@Query(value = "SELECT count(c.userId) FROM Cart as c WHERE c.userId = :userId")
	Integer minCartValue(int userId);
	
	@Query(value = "SELECT count(c.cartId) FROM Cart c INNER JOIN Product p ON (c.productId = p.productid) WHERE c.userId = :userId AND p.productid = :productId")
	Integer existingCartItem(int userId, long productId);
}
