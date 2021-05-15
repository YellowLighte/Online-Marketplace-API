package com.marketplace.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "product_order_items")
public class ProductOrderItem {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantity;

    public ProductOrderItem(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public ProductOrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductOrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
