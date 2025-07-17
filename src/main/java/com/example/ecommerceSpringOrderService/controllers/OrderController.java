package com.example.ecommerceSpringOrderService.controllers;

import com.example.ecommerceSpringOrderService.dtos.CreateOrderResponseDTO;
import com.example.ecommerceSpringOrderService.dtos.OrderRequestDTO;
import com.example.ecommerceSpringOrderService.dtos.ProductDTO;
import com.example.ecommerceSpringOrderService.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderService orderService ;

    public OrderController(OrderService orderService){
        this.orderService = orderService ;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponseDTO> getRequest(@RequestBody OrderRequestDTO orderRequestDTO) throws Exception {
        CreateOrderResponseDTO response =  this.orderService.createOrder(orderRequestDTO) ;

        return ResponseEntity.ok(response) ;
    }
}
