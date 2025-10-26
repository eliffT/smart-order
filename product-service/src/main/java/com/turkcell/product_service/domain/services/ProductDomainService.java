package com.turkcell.product_service.domain.services;

import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.valueobjects.ProductId;
import com.turkcell.product_service.domain.valueobjects.ProductName;

/**
 * Domain service for Product aggregate
 * Contains business logic that doesn't naturally belong to the Product entity
 */
public interface ProductDomainService {

    /**
     * Check if a product name is unique
     * 
     * @param name      the product name to check
     * @param excludeId product id to exclude from the check (for updates)
     * @return true if name is unique, false otherwise
     */
    boolean isProductNameUnique(ProductName name, ProductId excludeId);

    /**
     * Validate product before creation
     * 
     * @param product the product to validate
     * @throws IllegalArgumentException if product is invalid
     */
    void validateProductCreation(Product product);

    /**
     * Validate product before update
     * 
     * @param product the product to validate
     * @throws IllegalArgumentException if product is invalid for update
     */
    void validateProductUpdate(Product product);
}
