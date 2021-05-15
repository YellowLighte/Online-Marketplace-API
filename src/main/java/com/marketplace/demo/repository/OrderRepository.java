package com.marketplace.demo.repository;

import com.marketplace.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderComplete(boolean orderComplete);
}
