package com.example.juandmv.booking_microservice.controller;

import com.example.juandmv.booking_microservice.client.StockClient;
import com.example.juandmv.booking_microservice.dto.OrderDto;
import com.example.juandmv.booking_microservice.entity.Order;
import com.example.juandmv.booking_microservice.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockClient stockClient;

    private static final String CIRCUIT_BREAKER_NAME = "bookingService";

    @PostMapping("/order")
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "saveOrderFallback")
    public String saveOrder(@RequestBody OrderDto orderDto) {
        boolean inStock = orderDto.getOrderItems()
                .stream()
                .allMatch(orderItem -> stockClient.stockAvailable(orderItem.getCode()));

        if (!inStock) {
            throw new RuntimeException("Product not in stock, order cannot be saved!");
        }

        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        order.setOrderItems(orderDto.getOrderItems());

        orderRepository.save(order);

        return "Order saved";
    }

    public String saveOrderFallback(OrderDto orderDto, Throwable throwable) {
        return "Order cannot be processed at the moment. Please try again later!";
    }
}
