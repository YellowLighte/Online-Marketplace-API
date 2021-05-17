package com.marketplace.demo.service;

import com.marketplace.demo.exception.InformationExistException;
import com.marketplace.demo.exception.InformationNotFoundException;
import com.marketplace.demo.model.Order;
import com.marketplace.demo.repository.OrderRepository;
import com.marketplace.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //TODO Make checks and throw exceptions if user and/or order does not exist.
    public List<Order> getOrders() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orderList = orderRepository.findByUser_UserID(myUserDetails.getUser().getUserID());
        return orderList;
    }

    public Order getOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new InformationNotFoundException("order with id " + orderId + " not found.");
        }
    }

    //TODO: complete logic for below. Perhaps ask instructor during stand up?
    public Order createOrder(Order orderObject) {
        // check to see if user has existing order where orderComplete bool is false
//             if so, direct to that open order
            // if not, create new order
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Order> order = Optional.ofNullable(orderRepository
                .findByOrderCompleteAndUser_UserID(false, myUserDetails.getUser().getUserID()));
        System.out.println("This is the user that requested the new order: " + myUserDetails.getUser());
        System.out.println("This is the order that was just returned: " + order);
        if (order.isPresent()) {
            throw new InformationExistException("There is an open order that has not been completed.");
        } else {
            orderObject.setUser(myUserDetails.getUser());
            return orderRepository.save(orderObject);
        }
    }
}
