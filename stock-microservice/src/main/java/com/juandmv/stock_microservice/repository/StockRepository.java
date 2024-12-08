package com.juandmv.stock_microservice.repository;

import com.juandmv.stock_microservice.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findByCode(String code);
}
