package com.example.ecommerceSpringOrderService.mappers;

import com.example.ecommerceSpringOrderService.dtos.CreateOrderResponseDTO;
import com.example.ecommerceSpringOrderService.dtos.OrderRequestDTO;
import com.example.ecommerceSpringOrderService.entity.OrderEntity;
import com.example.ecommerceSpringOrderService.enums.OrderStatus;


public class OrderMapper {
    public static OrderEntity toOrderEntity(OrderRequestDTO orderRequestDTO){
        return OrderEntity.builder()
                .user_Id(orderRequestDTO.getUserId())
                .status(OrderStatus.PENDING)
                .build();
    }

    public static CreateOrderResponseDTO toCreateOrderResponseDTO(OrderEntity orderEntity){
        return CreateOrderResponseDTO.builder()
                .orderId(orderEntity.getId())
                .message(OrderStatus.PENDING)
                .build();
    }

}
