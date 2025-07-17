package com.example.ecommerceSpringOrderService.clients;


import com.example.ecommerceSpringOrderService.dtos.ProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductServiceClient {
    private final RestTemplateBuilder restTemplateBuilder ;

    ProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder ;
    }

    public ProductDTO getProductbyId(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build() ;
        String url = "http://localhost:3000/api/v1/products/" + id ;

        ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url , ProductDTO.class) ;

        return response.getBody() ;
    }
}
