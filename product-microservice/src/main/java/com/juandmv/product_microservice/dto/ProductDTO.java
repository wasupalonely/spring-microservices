package com.juandmv.product_microservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String productName;
    private String productDescription;
    private Double unitPrice;
}
