package com.example.juandmv.booking_microservice.dto;

import com.example.juandmv.booking_microservice.entity.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private List<OrderItem> orderItems;
}
