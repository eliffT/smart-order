package com.turkcell.product_service.domain.exceptions;

import com.turkcell.product_service.domain.valueobjects.ProductId;
import com.turkcell.product_service.domain.valueobjects.Stock;

/**
 * Exception thrown when there is insufficient stock for a product operation
 */
public class InsufficientStockException extends RuntimeException {

    private final ProductId productId;
    private final Stock requestedStock;
    private final Stock availableStock;

    public InsufficientStockException(ProductId productId, Stock requestedStock, Stock availableStock) {
        super(String.format("Insufficient stock for product %s. Requested: %d, Available: %d",
                productId, requestedStock.getValue(), availableStock.getValue()));
        this.productId = productId;
        this.requestedStock = requestedStock;
        this.availableStock = availableStock;
    }

    public ProductId getProductId() {
        return productId;
    }

    public Stock getRequestedStock() {
        return requestedStock;
    }

    public Stock getAvailableStock() {
        return availableStock;
    }
}


