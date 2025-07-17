package com.example.ecommerceSpringOrderService.repository;

import com.example.ecommerceSpringOrderService.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity , Long> {
}
