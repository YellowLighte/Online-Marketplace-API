package com.marketplace.demo.controller;

import com.marketplace.demo.model.Order;
import com.marketplace.demo.model.Product;
import com.marketplace.demo.model.ProductOrderItem;
import com.marketplace.demo.repository.OrderRepository;
import com.marketplace.demo.repository.ProductOrderItemRepository;
import com.marketplace.demo.repository.ProductRepository;
import com.marketplace.demo.service.ProductOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class ProductOrderItemController {

    private ProductOrderItemService productOrderItemService;

    @Autowired
    public void setProductOrderItemService(ProductOrderItemService productOrderItemService) {
        this.productOrderItemService = productOrderItemService;
    }

    // http://localhost:9092/api/cart/{productId}
    @PostMapping("/cart/{productId}")
    public ProductOrderItem createCartItem(@PathVariable Long productId, @RequestBody ProductOrderItem orderItem) {
        return productOrderItemService.createCartItem(productId, orderItem);
    }

    // http://localhost:9092/api/cart/{productOrderItemId}
    @DeleteMapping("/cart/{productOrderItemId}")
    public ResponseEntity<HashMap> deleteCartItem(@PathVariable Long productOrderItemId){
        String status = productOrderItemService.deleteCartItem(productOrderItemId);
        HashMap message = new HashMap();
        message.put("message", status);
        return new ResponseEntity<HashMap>(message, HttpStatus.OK);
    }


}
