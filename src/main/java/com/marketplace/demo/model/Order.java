package com.marketplace.demo.model;

import java.util.Date;

public class Order {

    private Long orderID;
    private double totalCost;
    private Date orderDate;
    private boolean orderComplete;

    public Order(Long orderID, double totalCost, Date orderDate, boolean orderComplete) {
        this.orderID = orderID;
        this.totalCost = totalCost;
        this.orderDate = orderDate;
        this.orderComplete = orderComplete;
    }

    public Order() {
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isOrderComplete() {
        return orderComplete;
    }

    public void setOrderComplete(boolean orderComplete) {
        this.orderComplete = orderComplete;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", totalCost=" + totalCost +
                ", orderDate=" + orderDate +
                ", orderComplete=" + orderComplete +
                '}';
    }
}
