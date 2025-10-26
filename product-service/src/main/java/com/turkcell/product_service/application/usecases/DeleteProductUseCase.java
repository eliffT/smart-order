package com.turkcell.product_service.application.usecases;

import com.turkcell.product_service.application.exceptions.InvalidProductIdException;
import com.turkcell.product_service.application.exceptions.ProductNotFoundException;
import com.turkcell.product_service.application.mappers.ProductApplicationMapper;
import com.turkcell.product_service.domain.repositories.ProductRepository;
import com.turkcell.product_service.domain.valueobjects.ProductId;
import org.springframework.stereotype.Component;

/**
 * Use case for deleting a product
 */
@Component
public class DeleteProductUseCase {

    private final ProductRepository productRepository;

    public DeleteProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(String productId) {
        try {
            ProductId id = ProductApplicationMapper.toProductId(productId);

            // Check if product exists
            if (!productRepository.existsById(id)) {
                throw new ProductNotFoundException("Product not found with ID: " + productId);
            }

            // Delete product
            productRepository.deleteById(id);
        } catch (InvalidProductIdException e) {
            throw e; // Re-throw InvalidProductIdException
        }
    }
}
