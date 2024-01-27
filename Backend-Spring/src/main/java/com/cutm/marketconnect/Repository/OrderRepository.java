package com.cutm.marketconnect.repository;

import com.cutm.marketconnect.modal.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT o FROM Order o WHERE o.user.id = :userId AND (o.orderStatus = PLACED OR o.orderStatus = CONFIRMED OR o.orderStatus = SHIPPED OR o.orderStatus = DELIVERED)")
	public List<Order> getUsersOrders(@Param("userId") Long userId);
}
