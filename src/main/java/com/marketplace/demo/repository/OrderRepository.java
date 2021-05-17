package com.marketplace.demo.repository;

import com.marketplace.demo.model.Order;
import com.marketplace.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_UserID(Long id);
    Order findByOrderCompleteAndUser_UserID(boolean bool, Long id);
    Order findByUserAndOrderID(User user, Long id);

}
