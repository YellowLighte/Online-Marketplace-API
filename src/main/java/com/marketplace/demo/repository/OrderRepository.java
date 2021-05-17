package com.marketplace.demo.repository;

import com.marketplace.demo.model.Order;
import com.marketplace.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderComplete(boolean orderComplete);
//    Order findByUser(User user);
    Order findByUser_UserID(Long id);

}
