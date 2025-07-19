package com.example.ecommerceSpringOrderService.services;

import com.example.ecommerceSpringOrderService.clients.ProductServiceClient;
import com.example.ecommerceSpringOrderService.dtos.*;
import com.example.ecommerceSpringOrderService.entity.OrderEntity;
import com.example.ecommerceSpringOrderService.entity.OrderItemEntity;
import com.example.ecommerceSpringOrderService.enums.OrderStatus;
import com.example.ecommerceSpringOrderService.mappers.OrderMapper;
import com.example.ecommerceSpringOrderService.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository ;
    private final ProductServiceClient productServiceClient ;

    public OrderService(OrderRepository orderRepository ,
                        ProductServiceClient productServiceClient
    ){
        this.orderRepository = orderRepository ;
        this.productServiceClient = productServiceClient ;
    }

    @Override
    public CreateOrderResponseDTO createOrder(OrderRequestDTO requestDTO) throws Exception {
        OrderEntity order = this.orderRepository.save(OrderMapper.toOrderEntity(requestDTO)) ;

        List<OrderItemEntity> items = new ArrayList<>() ;
        for(OrderItemDTO item : requestDTO.getItems()){
            ProductDTO product = this.productServiceClient.getProductbyId(item.getProductId()) ;
            double unitPrice = product.getPrice() ;
            int quantity = item.getQuantity() ;

            OrderItemEntity orderItemEntity = OrderItemEntity.builder()
                    .orderEntity(order)
                    .perUnitPrice(unitPrice)
                    .totalPrice(unitPrice * quantity)
                    .quantity(quantity)
                    .product_id(item.getProductId())
                    .build();

            items.add(orderItemEntity) ;
        }
        order.setItems(items);
        OrderEntity createdOrder = this.orderRepository.save(order) ;
        return OrderMapper.toCreateOrderResponseDTO(createdOrder) ;
    }

    @Override
    public CreateOrderResponseDTO confirmPayment(Long id) throws Exception {
        OrderEntity orderEntity = this.orderRepository.findById(id)
                .orElseThrow(() -> new Exception("Invalid Order-Id")) ;

        if(orderEntity.getStatus() == OrderStatus.CANCELLED || orderEntity.getStatus() == OrderStatus.SUCCESS){
            throw new Exception("Order either is cancelled or confirmed") ;
        }

        orderEntity.setStatus(OrderStatus.SUCCESS);
        orderEntity = this.orderRepository.save(orderEntity) ;

        return OrderMapper.toCreateOrderResponseDTO(orderEntity) ;
    }
}
