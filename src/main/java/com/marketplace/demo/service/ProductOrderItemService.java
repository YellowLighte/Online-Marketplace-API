package com.marketplace.demo.service;

import com.marketplace.demo.exception.InformationNotFoundException;
import com.marketplace.demo.model.Order;
import com.marketplace.demo.model.Product;
import com.marketplace.demo.model.ProductOrderItem;
import com.marketplace.demo.repository.OrderRepository;
import com.marketplace.demo.repository.ProductOrderItemRepository;
import com.marketplace.demo.repository.ProductRepository;
import com.marketplace.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderItemService {

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

    public ProductOrderItem createCartItem(Long productId, ProductOrderItem orderItem) {

        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Order> order = Optional.ofNullable(orderRepository.findByOrderCompleteAndUser_UserID(false, myUserDetails.getUser().getUserID()));
        Optional<Product> product = productRepository.findById(productId);

        if (!order.isPresent()) {
            throw new InformationNotFoundException("User does not have an existing open order.");
        }
        if (!product.isPresent()) {
            throw new InformationNotFoundException("Product with id " + productId + " not found.");
        }

        orderItem.setOrder(order.get());
        orderItem.setProduct(product.get());
        orderItem.setQuantity(orderItem.getQuantity());
        return productOrderItemRepository.save(orderItem);
    }

    public String deleteCartItem(Long productOrderItemId) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Order> order = Optional.ofNullable(orderRepository.findByOrderCompleteAndUser_UserID(false, myUserDetails.getUser().getUserID()));
        Optional<ProductOrderItem> cartItem = Optional.ofNullable(productOrderItemRepository.findById(productOrderItemId).get());

        if (!order.isPresent()) {
            throw new InformationNotFoundException("User does not have an open order.");
        }
        if (!cartItem.isPresent()) {
            throw new InformationNotFoundException("Cart item with id " + cartItem.get().getId() + " does not exist.");
        }
        productOrderItemRepository.deleteById(productOrderItemId);
        return "Item deleted successfully.";
    }

//    // Test to get cart price.
//    public double calculateCartCost() {
//        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Optional<Order> order = Optional.ofNullable(orderRepository.findByOrderCompleteAndUser_UserID(false, myUserDetails.getUser().getUserID()));
//
//        List<ProductOrderItem> itemsInCart = productOrderItemRepository.findByOrder_OrderID(order.get().getOrderID());
//
//        double totalCost = 0;
//
//        for (ProductOrderItem item : itemsInCart) {
//            totalCost += item.getProduct().getPrice() * item.getQuantity();
//        }
//
//        return totalCost;
//    }


}
