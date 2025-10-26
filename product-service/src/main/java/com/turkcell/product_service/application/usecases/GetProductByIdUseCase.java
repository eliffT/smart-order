package com.turkcell.product_service.application.usecases;

import com.turkcell.product_service.application.dtos.ProductResponse;
import com.turkcell.product_service.application.exceptions.InvalidProductIdException;
import com.turkcell.product_service.application.exceptions.ProductNotFoundException;
import com.turkcell.product_service.application.mappers.ProductApplicationMapper;
import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.repositories.ProductRepository;
import com.turkcell.product_service.domain.valueobjects.ProductId;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Use case for getting a product by ID
 */
@Component
public class GetProductByIdUseCase {

    private final ProductRepository productRepository;

    public GetProductByIdUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse execute(String productId) {
        try {
            ProductId id = ProductApplicationMapper.toProductId(productId);
            Optional<Product> product = productRepository.findById(id);

            if (product.isEmpty()) {
                throw new ProductNotFoundException("Product not found with ID: " + productId);
            }

            return ProductApplicationMapper.toResponse(product.get());
        } catch (InvalidProductIdException e) {
            throw e; // Re-throw InvalidProductIdException
        }
    }
}
