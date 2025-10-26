package com.turkcell.product_service.application.usecases;

import com.turkcell.product_service.application.dtos.ProductResponse;
import com.turkcell.product_service.application.dtos.UpdateProductRequest;
import com.turkcell.product_service.application.exceptions.InvalidProductIdException;
import com.turkcell.product_service.application.exceptions.ProductNotFoundException;
import com.turkcell.product_service.application.mappers.ProductApplicationMapper;
import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.repositories.ProductRepository;
import com.turkcell.product_service.domain.valueobjects.ProductId;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Use case for updating a product
 */
@Component
public class UpdateProductUseCase {

    private final ProductRepository productRepository;

    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse execute(String productId, UpdateProductRequest request) {
        try {
            ProductId id = ProductApplicationMapper.toProductId(productId);
            Optional<Product> productOpt = productRepository.findById(id);

            if (productOpt.isEmpty()) {
                throw new ProductNotFoundException("Product not found with ID: " + productId);
            }

            Product product = productOpt.get();

            // Update product with new data
            ProductApplicationMapper.updateDomain(product, request);

            // Save updated product
            Product updatedProduct = productRepository.save(product);

            // Convert to response
            return ProductApplicationMapper.toResponse(updatedProduct);
        } catch (InvalidProductIdException e) {
            throw e; // Re-throw InvalidProductIdException
        }
    }
}
