package com.example.ecommerceSpringOrderService.clients;


import com.example.ecommerceSpringOrderService.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductServiceClient {
    private final RestTemplate restTemplate;

    ProductServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate ;
    }

    public ProductDTO getProductbyId(Long id){
        String url = "http://ecommerceSpring/api/v1/products/" + id ;

        ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url , ProductDTO.class) ;

        return response.getBody() ;
    }
}
