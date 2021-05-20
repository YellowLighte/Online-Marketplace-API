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
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Order> order = Optional.ofNullable(orderRepository.findByUserAndOrderID(myUserDetails.getUser(), orderId));

        if (order.isPresent()) {
            return order.get();
        } else {
            throw new InformationNotFoundException("order with id " + orderId + " not found.");
        }
    }

    public List<Order> getClosedOrders() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orderList = orderRepository.findByUser_UserIDAndOrderComplete(myUserDetails.getUser().getUserID(), true);

        if (orderList.isEmpty()) {
            throw new InformationNotFoundException("No closed orders for user " + myUserDetails.getUser().getUserName() + ".");
        }

        return orderList;
    }

    public Optional<Order> getOpenOrder() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Order> order = Optional.ofNullable(orderRepository.findByUser_UserIDAndOrderComplete(myUserDetails.getUser().getUserID(), false).get(0));

        if (!order.isPresent()) {
            throw new InformationNotFoundException("No open orders for user " + myUserDetails.getUser().getUserName() + ".");
        }

        return order;
    }

    //TODO: Ask Matt about this - directing the User to the existing Order if they try to create a new order
    public Order createOrder(Order orderObject) {

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

    // Test to get cart price.
//    public double calculateCartCost() {
//        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Optional<Order> order = Optional.ofNullable(orderRepository.findByOrderCompleteAndUser_UserID(false, myUserDetails.getUser().getUserID()));
//
//        List<ProductOrderItem> itemsInCart = order.get().getOrderProducts();
//
//        double totalCost = 0;
//
//        if (!itemsInCart.isEmpty()) {
//            for (ProductOrderItem item : itemsInCart) {
//                totalCost += item.getProduct().getPrice() * item.getQuantity();
//            }
//        }
//
//        return totalCost;
//    }
}
