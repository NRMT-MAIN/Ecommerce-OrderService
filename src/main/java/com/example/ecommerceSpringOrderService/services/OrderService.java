package com.example.ecommerceSpringOrderService.services;

import com.example.ecommerceSpringOrderService.clients.ProductServiceClient;
import com.example.ecommerceSpringOrderService.dtos.CreateOrderResponseDTO;
import com.example.ecommerceSpringOrderService.dtos.OrderItemDTO;
import com.example.ecommerceSpringOrderService.dtos.OrderRequestDTO;
import com.example.ecommerceSpringOrderService.dtos.ProductDTO;
import com.example.ecommerceSpringOrderService.entity.OrderEntity;
import com.example.ecommerceSpringOrderService.entity.OrderItemEntity;
import com.example.ecommerceSpringOrderService.mappers.OrderMapper;
import com.example.ecommerceSpringOrderService.repository.OrderItemsRepository;
import com.example.ecommerceSpringOrderService.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderItemsRepository orderItemsRepository ;
    private final OrderRepository orderRepository ;
    private final ProductServiceClient productServiceClient ;

    public OrderService(OrderRepository orderRepository ,
                        OrderItemsRepository orderItemsRepository ,
                        ProductServiceClient productServiceClient
    ){
        this.orderItemsRepository = orderItemsRepository ;
        this.orderRepository = orderRepository ;
        this.productServiceClient = productServiceClient ;
    }

    @Override
    public CreateOrderResponseDTO createOrder(OrderRequestDTO requestDTO) throws Exception {
        OrderEntity order = this.orderRepository.save(OrderMapper.toOrderEntity(requestDTO)) ;

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

            this.orderItemsRepository.save(orderItemEntity) ;
        }
        return OrderMapper.toCreateOrderResponseDTO(order) ;
    }
}
