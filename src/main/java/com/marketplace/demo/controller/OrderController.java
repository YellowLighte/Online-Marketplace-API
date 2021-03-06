package com.marketplace.demo.controller;

import com.marketplace.demo.model.Order;
import com.marketplace.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
    this.orderService = orderService;
    }

    // http://localhost:9092/api/orders
    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order orderObject) {
        return orderService.createOrder(orderObject);
    }

    // http://localhost:9092/api/orders
    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    // http://localhost:9092/api/orders/{orderId}
    @GetMapping("/orders/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    // http://localhost:9092/api/orders/closed
    @GetMapping("/orders/closed")
    public List<Order> getClosedOrders() {
        return orderService.getClosedOrders();
    }

    // http://localhost:9092/api/orders/open
    @GetMapping("/orders/open")
    public Optional<Order> getOpenOrder() {
        return orderService.getOpenOrder();
    }

    // http://localhost:9092/api/orders/{orderId}
    @PutMapping("/orders/{orderId}")
    public Order updateOrderStatus(@PathVariable Long orderId) {
        return orderService.updateOrderStatus(orderId);
    }

}
