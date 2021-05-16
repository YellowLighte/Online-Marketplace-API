package com.marketplace.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    @Column
    private double totalCost;

    @Column
    private Date orderDate;

    @Column
    private boolean orderComplete;

    @OneToMany(mappedBy = "order")
    private List<ProductOrderItem> orderProducts;


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
                ", orderProducts=" + orderProducts +
                '}';
    }

    public List<ProductOrderItem> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<ProductOrderItem> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
