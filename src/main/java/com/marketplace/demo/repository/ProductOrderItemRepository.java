package com.marketplace.demo.repository;

import com.marketplace.demo.model.Product;
import com.marketplace.demo.model.ProductOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderItemRepository extends JpaRepository<ProductOrderItem, Long> {
    List<ProductOrderItem> findByOrder_OrderID(Long orderID);

}
