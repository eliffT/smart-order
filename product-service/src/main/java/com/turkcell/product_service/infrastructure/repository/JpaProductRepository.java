package com.turkcell.product_service.infrastructure.repository;

import com.turkcell.product_service.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findByNameContaining(String name);

    List<ProductEntity> findByStockGreaterThan(Integer quantity);

    List<ProductEntity> findByStockEquals(Integer quantity);

    List<ProductEntity> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
