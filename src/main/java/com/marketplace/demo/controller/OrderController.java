package com.marketplace.demo.controller;

import com.marketplace.demo.exception.InformationNotFoundException;
import com.marketplace.demo.model.Order;
import com.marketplace.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class OrderController {

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping("/orders")
    public String createOrder() {
        System.out.println("OrderController calling createOrder");
        return "order created.";
        // check to see if user has an existing order that has not been completed
            // if yes, do not create new order
            // if no, create new order
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{orderId}")
    public Optional<Order> getOrder(@PathVariable Long orderId) {
        Optional order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order;
        } else {
            throw new InformationNotFoundException("order with id " + orderId + " not found.");
        }
    }
}
