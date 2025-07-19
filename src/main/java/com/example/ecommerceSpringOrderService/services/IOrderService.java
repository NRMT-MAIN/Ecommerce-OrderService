package com.example.ecommerceSpringOrderService.services;

import com.example.ecommerceSpringOrderService.dtos.CreateOrderResponseDTO;
import com.example.ecommerceSpringOrderService.dtos.OrderRequestDTO;

public interface IOrderService {
    CreateOrderResponseDTO createOrder(OrderRequestDTO requestDTO) throws Exception ;
    CreateOrderResponseDTO confirmPayment(Long id) throws Exception ;
}
