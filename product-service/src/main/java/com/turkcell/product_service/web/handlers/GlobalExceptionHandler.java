package com.turkcell.product_service.web.handlers;

import com.turkcell.product_service.application.exceptions.InvalidProductIdException;
import com.turkcell.product_service.application.exceptions.ProductNotFoundException;
import com.turkcell.product_service.web.dtos.ErrorWebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for the web layer
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handle validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorWebResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {

        logger.warn("Validation error occurred: {}", ex.getMessage());

        List<ErrorWebResponse.FieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::mapFieldError)
                .collect(Collectors.toList());

        ErrorWebResponse errorResponse = new ErrorWebResponse(
                "Validation failed",
                "VALIDATION_ERROR",
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false).replace("uri=", ""),
                fieldErrors);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Handle invalid product ID exceptions
     */
    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ErrorWebResponse> handleInvalidProductIdException(
            InvalidProductIdException ex, WebRequest request) {

        logger.warn("Invalid product ID: {}", ex.getMessage());

        ErrorWebResponse errorResponse = new ErrorWebResponse(
                ex.getMessage(),
                "INVALID_PRODUCT_ID",
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false).replace("uri=", ""),
                null);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Handle product not found exceptions
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorWebResponse> handleProductNotFoundException(
            ProductNotFoundException ex, WebRequest request) {

        logger.warn("Product not found: {}", ex.getMessage());

        ErrorWebResponse errorResponse = new ErrorWebResponse(
                ex.getMessage(),
                "PRODUCT_NOT_FOUND",
                HttpStatus.NOT_FOUND.value(),
                request.getDescription(false).replace("uri=", ""),
                null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Handle illegal argument exceptions
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorWebResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {

        logger.warn("Illegal argument: {}", ex.getMessage());

        ErrorWebResponse errorResponse = new ErrorWebResponse(
                ex.getMessage(),
                "ILLEGAL_ARGUMENT",
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false).replace("uri=", ""),
                null);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Handle illegal state exceptions
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorWebResponse> handleIllegalStateException(
            IllegalStateException ex, WebRequest request) {

        logger.warn("Illegal state: {}", ex.getMessage());

        ErrorWebResponse errorResponse = new ErrorWebResponse(
                ex.getMessage(),
                "ILLEGAL_STATE",
                HttpStatus.CONFLICT.value(),
                request.getDescription(false).replace("uri=", ""),
                null);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Handle generic exceptions
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorWebResponse> handleGenericException(
            Exception ex, WebRequest request) {

        logger.error("Unexpected error occurred", ex);

        ErrorWebResponse errorResponse = new ErrorWebResponse(
                "An unexpected error occurred",
                "INTERNAL_SERVER_ERROR",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getDescription(false).replace("uri=", ""),
                null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    /**
     * Map Spring FieldError to custom FieldError
     */
    private ErrorWebResponse.FieldError mapFieldError(FieldError fieldError) {
        return new ErrorWebResponse.FieldError(
                fieldError.getField(),
                fieldError.getDefaultMessage(),
                fieldError.getRejectedValue());
    }
}
