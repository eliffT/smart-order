package com.turkcell.product_service.application.exceptions;

/**
 * Exception thrown when a product ID is invalid
 */
public class InvalidProductIdException extends RuntimeException {

    public InvalidProductIdException(String message) {
        super(message);
    }

    public InvalidProductIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
