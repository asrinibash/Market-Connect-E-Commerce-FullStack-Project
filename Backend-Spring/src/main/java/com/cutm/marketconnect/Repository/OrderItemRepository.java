package com.cutm.marketconnect.repository;

import com.cutm.marketconnect.modal.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
