package com.turkcell.product_service.application.usecases;

import com.turkcell.product_service.application.dtos.CreateProductRequest;
import com.turkcell.product_service.application.dtos.ProductResponse;
import com.turkcell.product_service.application.mappers.ProductApplicationMapper;
import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.repositories.ProductRepository;
import org.springframework.stereotype.Component;

/**
 * Use case for creating a new product
 */
@Component
public class CreateProductUseCase {

    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse execute(CreateProductRequest request) {
        // Convert DTO to domain entity
        Product product = ProductApplicationMapper.toDomain(request);

        // Save product using repository
        Product savedProduct = productRepository.save(product);

        // Convert domain entity to response DTO
        return ProductApplicationMapper.toResponse(savedProduct);
    }
}
