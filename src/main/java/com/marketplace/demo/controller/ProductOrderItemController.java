package com.marketplace.demo.controller;

import com.marketplace.demo.model.Order;
import com.marketplace.demo.model.Product;
import com.marketplace.demo.model.ProductOrderItem;
import com.marketplace.demo.repository.OrderRepository;
import com.marketplace.demo.repository.ProductOrderItemRepository;
import com.marketplace.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class ProductOrderItemController {

    private ProductOrderItemRepository productOrderItemRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductOrderItemRepository(ProductOrderItemRepository productOrderItemRepository) {
        this.productOrderItemRepository = productOrderItemRepository;
    }


    //TODO: Create checks so that it'll throw errors if the product or order number doesn't exist.
    // http://localhost:9092/api/cart/{orderId}/{productId}
    @PostMapping("/cart/{orderId}/{productId}")
    public ProductOrderItem create(@PathVariable Long orderId, @PathVariable Long productId,
                                   @RequestBody ProductOrderItem orderItem) {
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Product> product = productRepository.findById(productId);
        orderItem.setOrder(order.get());
        orderItem.setProduct(product.get());
        orderItem.setQuantity(orderItem.getQuantity());
        return productOrderItemRepository.save(orderItem);
    }
}
