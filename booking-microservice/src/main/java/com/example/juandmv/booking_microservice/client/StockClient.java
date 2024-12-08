package com.example.juandmv.booking_microservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "stock-microservice")
public interface StockClient {

    @PostMapping("/api/stock/stock/available/{code}")
    boolean stockAvailable(@PathVariable String code);
}
