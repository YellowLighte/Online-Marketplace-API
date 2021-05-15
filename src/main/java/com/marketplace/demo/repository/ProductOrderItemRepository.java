package com.marketplace.demo.repository;

import com.marketplace.demo.model.ProductOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderItemRepository extends JpaRepository<ProductOrderItem, Long> {
}
