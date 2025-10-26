package com.turkcell.product_service.infrastructure.mapper;

import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.valueobjects.*;
import com.turkcell.product_service.infrastructure.entities.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class responsible for converting between domain Product entity
 * and infrastructure ProductEntity
 */
public class ProductMapper {

    /**
     * Converts ProductEntity (infrastructure) to Product (domain)
     */
    public static Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }

        return Product.reconstruct(
                ProductId.of(entity.getId()),
                ProductName.of(entity.getName()),
                Description.of(entity.getDescription()),
                Price.of(entity.getPrice()),
                Currency.of(entity.getCurrency()),
                Stock.of(entity.getStock()),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    /**
     * Converts Product (domain) to ProductEntity (infrastructure)
     */
    public static ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductEntity(
                product.getId().getValue(),
                product.getName().getValue(),
                product.getDescription().getValue(),
                product.getPrice().getValue(),
                product.getCurrency().getValue(),
                product.getStock().getValue());
    }

    /**
     * Converts a list of ProductEntity to a list of Product
     */
    public static List<Product> toDomainList(List<ProductEntity> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(ProductMapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of Product to a list of ProductEntity
     */
    public static List<ProductEntity> toEntityList(List<Product> products) {
        if (products == null) {
            return null;
        }

        return products.stream()
                .map(ProductMapper::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * Updates an existing ProductEntity with data from a Product domain object
     * This method preserves the original timestamps and only updates business data
     */
    public static void updateEntity(ProductEntity entity, Product product) {
        if (entity == null || product == null) {
            return;
        }

        entity.setName(product.getName().getValue());
        entity.setDescription(product.getDescription().getValue());
        entity.setPrice(product.getPrice().getValue());
        entity.setCurrency(product.getCurrency().getValue());
        entity.setStock(product.getStock().getValue());
        // Note: createdAt and updatedAt are handled by @PreUpdate annotation
    }
}