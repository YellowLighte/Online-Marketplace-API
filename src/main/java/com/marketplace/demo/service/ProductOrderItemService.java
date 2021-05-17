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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    //TODO: Create checks so that it'll throw errors if the product or order number doesn't exist.
    // Ask Matt if there is a better way to do this than passing the orderId and productId through the Path
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

}
