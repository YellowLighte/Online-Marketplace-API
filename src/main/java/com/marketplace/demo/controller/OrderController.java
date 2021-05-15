package com.marketplace.demo.controller;

import com.marketplace.demo.exception.InformationExistException;
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

    // http://localhost:9092/api/orders
    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order orderObject) {
        System.out.println("calling createOrder() -->");
        Order order = orderRepository.findByOrderComplete(false);
        if (order != null) {
            throw new InformationExistException("open order exists");
        } else {
            return orderRepository.save(orderObject);
        }
    }

    // http://localhost:9092/api/orders
    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    // http://localhost:9092/api/orders/{orderId}
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
