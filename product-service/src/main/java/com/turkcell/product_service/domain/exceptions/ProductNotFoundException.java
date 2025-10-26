package com.turkcell.product_service.domain.exceptions;

import com.turkcell.product_service.domain.valueobjects.ProductId;

/**
 * Exception thrown when a product is not found
 */
public class ProductNotFoundException extends RuntimeException {

    private final ProductId productId;

    public ProductNotFoundException(ProductId productId) {
        super("Product not found with id: " + productId);
        this.productId = productId;
    }

    public ProductNotFoundException(ProductId productId, String message) {
        super(message);
        this.productId = productId;
    }

    public ProductId getProductId() {
        return productId;
    }
}


