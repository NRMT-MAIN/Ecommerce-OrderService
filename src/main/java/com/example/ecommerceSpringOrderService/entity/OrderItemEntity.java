package com.example.ecommerceSpringOrderService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_items")
public class OrderItemEntity extends BaseEntity {
    private Long product_id ;
    private int quantity ;
    private double perUnitPrice ;
    private double totalPrice ;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderEntity orderEntity ;
}
