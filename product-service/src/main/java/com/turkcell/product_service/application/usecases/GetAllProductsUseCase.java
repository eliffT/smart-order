package com.turkcell.product_service.application.usecases;

import com.turkcell.product_service.application.dtos.ProductListResponse;
import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.repositories.ProductRepository;
import com.turkcell.product_service.application.mappers.ProductApplicationMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Use case for getting all products
 */
@Component
public class GetAllProductsUseCase {

    private final ProductRepository productRepository;

    public GetAllProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductListResponse execute() {
        List<Product> products = productRepository.findAll();
        return ProductApplicationMapper.toListResponse(products);
    }
}
