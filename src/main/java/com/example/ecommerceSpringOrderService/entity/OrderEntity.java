package com.example.ecommerceSpringOrderService.entity;

import com.example.ecommerceSpringOrderService.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
    private Long user_Id ;

    @Enumerated(EnumType.STRING)
    private OrderStatus status ;

    @OneToMany(mappedBy = "orderEntity" , cascade = CascadeType.ALL)
    private List<OrderItemEntity> items ;
}
