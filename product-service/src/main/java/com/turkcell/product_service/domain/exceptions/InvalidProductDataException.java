package com.turkcell.product_service.domain.exceptions;

/**
 * Exception thrown when product data is invalid
 */
public class InvalidProductDataException extends RuntimeException {

    public InvalidProductDataException(String message) {
        super(message);
    }

    public InvalidProductDataException(String message, Throwable cause) {
        super(message, cause);
    }
}


