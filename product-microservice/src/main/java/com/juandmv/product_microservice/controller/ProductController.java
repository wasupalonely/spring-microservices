package com.juandmv.product_microservice.controller;

import com.juandmv.product_microservice.entity.ProductEntity;
import com.juandmv.product_microservice.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductEntity productEntity) {
        System.out.println("Received: " + productEntity);
        System.out.println("Product Name: " + productEntity.getProductName());
        System.out.println("Description: " + productEntity.getProductDescription());
        System.out.println("Unit Price: " + productEntity.getUnitPrice());
        productRepository.save(productEntity);
    }

}
