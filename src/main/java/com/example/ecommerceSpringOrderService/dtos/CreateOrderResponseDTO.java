package com.example.ecommerceSpringOrderService.dtos;

import com.example.ecommerceSpringOrderService.enums.OrderStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderResponseDTO {
    private long orderId ;
    private OrderStatus message ;
}
