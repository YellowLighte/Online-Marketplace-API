package com.marketplace.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class OrderController {

    @PostMapping("/orders")
    public String createOrder() {
        System.out.println("OrderController calling createOrder");
        return "order created.";
        // check to see if user has an existing order that has not been completed
            // if yes, do not create new order
            // if no, create new order
    }
}
