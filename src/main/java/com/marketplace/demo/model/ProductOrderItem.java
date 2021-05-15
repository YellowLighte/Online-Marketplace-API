package com.marketplace.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_order_items")
public class ProductOrderItem {

    @Id
    private Long id;
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
