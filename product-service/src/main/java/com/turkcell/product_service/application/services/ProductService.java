package com.turkcell.product_service.application.services;

import com.turkcell.product_service.application.dtos.*;
import com.turkcell.product_service.application.ports.ProductServicePort;
import com.turkcell.product_service.application.usecases.*;
import org.springframework.stereotype.Service;

/**
 * Service implementation for product operations
 * This service orchestrates use cases to fulfill business requirements
 */
@Service
public class ProductService implements ProductServicePort {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductService(CreateProductUseCase createProductUseCase,
            GetProductByIdUseCase getProductByIdUseCase,
            GetAllProductsUseCase getAllProductsUseCase,
            UpdateProductUseCase updateProductUseCase,
            DeleteProductUseCase deleteProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        return createProductUseCase.execute(request);
    }

    @Override
    public ProductResponse getProductById(String id) {
        return getProductByIdUseCase.execute(id);
    }

    @Override
    public ProductListResponse getAllProducts() {
        return getAllProductsUseCase.execute();
    }

    @Override
    public ProductResponse updateProduct(String id, UpdateProductRequest request) {
        return updateProductUseCase.execute(id, request);
    }

    @Override
    public void deleteProduct(String id) {
        deleteProductUseCase.execute(id);
    }
}