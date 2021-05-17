package com.marketplace.demo.service;

import com.marketplace.demo.exception.InformationExistException;
import com.marketplace.demo.exception.InformationNotFoundException;
import com.marketplace.demo.model.Order;
import com.marketplace.demo.repository.OrderRepository;
import com.marketplace.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orderList = orderRepository.findByUser_UserID(myUserDetails.getUser().getUserID());

        if (orderList.isEmpty()) {
            throw new InformationNotFoundException("No existing orders found for user " + myUserDetails.getUser().getUserName());
        } else {
            return orderList;
        }
    }

    public Order getOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new InformationNotFoundException("order with id " + orderId + " not found.");
        }
    }

    //TODO: Ask Matt about this - directing the User to the existing Order if they try to create a new order
    public Order createOrder(Order orderObject) {
        // check to see if user has existing order where orderComplete bool is false
//             if so, direct to that open order
            // if not, create new order
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Order> order = Optional.ofNullable(orderRepository
                .findByOrderCompleteAndUser_UserID(false, myUserDetails.getUser().getUserID()));

        if (order.isPresent()) {
            throw new InformationExistException("There is an open order that has not been completed.");
        } else {
            orderObject.setUser(myUserDetails.getUser());
            return orderRepository.save(orderObject);
        }
    }

    public Order updateOrderStatus(Long orderId) {

        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Order> order = Optional.ofNullable(orderRepository.findByOrderCompleteAndUser_UserID(false, myUserDetails.getUser().getUserID()));

        if (!order.isPresent()) {
            throw new InformationNotFoundException("No open order with id " + orderId + " found.");
        } else {
            order.get().setOrderComplete(true);
            return orderRepository.save(order.get());
        }
    }
}
