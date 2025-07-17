package com.example.ecommerceSpringOrderService.repository;

import com.example.ecommerceSpringOrderService.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItemEntity , Long> {
}
