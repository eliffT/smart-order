package com.turkcell.product_service.application.mappers;

import com.turkcell.product_service.application.dtos.*;
import com.turkcell.product_service.application.exceptions.InvalidProductIdException;
import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.valueobjects.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between application DTOs and domain entities
 */
public class ProductApplicationMapper {

    /**
     * Converts CreateProductRequest to domain Product
     */
    public static Product toDomain(CreateProductRequest request) {
        if (request == null) {
            return null;
        }

        return Product.create(
                ProductName.of(request.getName()),
                Description.of(request.getDescription()),
                Price.of(request.getPrice()),
                Currency.of(request.getCurrency()),
                Stock.of(request.getStock()));
    }

    /**
     * Converts domain Product to ProductResponse
     */
    public static ProductResponse toResponse(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductResponse(
                product.getId().getValue().toString(),
                product.getName().getValue(),
                product.getDescription().getValue(),
                product.getPrice().getValue(),
                product.getCurrency().getValue(),
                product.getStock().getValue(),
                product.getCreatedAt(),
                product.getUpdatedAt());
    }

    /**
     * Converts string ID to ProductId with validation
     */
    public static ProductId toProductId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidProductIdException("Product ID cannot be null or empty");
        }

        try {
            UUID uuid = UUID.fromString(id.trim());
            return ProductId.of(uuid);
        } catch (IllegalArgumentException e) {
            throw new InvalidProductIdException("Invalid UUID format: " + id, e);
        }
    }

    /**
     * Converts list of domain Products to ProductListResponse
     */
    public static ProductListResponse toListResponse(List<Product> products) {
        if (products == null) {
            return new ProductListResponse();
        }

        List<ProductResponse> productResponses = products.stream()
                .map(ProductApplicationMapper::toResponse)
                .collect(Collectors.toList());

        return new ProductListResponse(productResponses);
    }

    /**
     * Updates domain Product with UpdateProductRequest data
     */
    public static void updateDomain(Product product, UpdateProductRequest request) {
        if (product == null || request == null) {
            return;
        }

        if (request.getName() != null) {
            product.updateInfo(
                    ProductName.of(request.getName()),
                    request.getDescription() != null ? Description.of(request.getDescription())
                            : product.getDescription(),
                    request.getPrice() != null ? Price.of(request.getPrice()) : product.getPrice(),
                    request.getCurrency() != null ? Currency.of(request.getCurrency()) : product.getCurrency());
        }

        if (request.getStock() != null) {
            int currentStock = product.getStock().getValue();
            int newStock = request.getStock();

            if (newStock > currentStock) {
                product.addStock(newStock - currentStock);
            } else if (newStock < currentStock) {
                product.reduceStock(currentStock - newStock);
            }
        }
    }
}
