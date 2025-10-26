package com.turkcell.product_service.application.ports;

import com.turkcell.product_service.application.dtos.*;

/**
 * Port interface for product service operations
 * This interface defines the contract for product management
 */
public interface ProductServicePort {

    /**
     * Create a new product
     * 
     * @param request the product creation request
     * @return the created product response
     */
    ProductResponse createProduct(CreateProductRequest request);

    /**
     * Get a product by its ID
     * 
     * @param id the product ID
     * @return the product response
     * @throws com.turkcell.product_service.application.exceptions.InvalidProductIdException if
     *                                                                                       ID
     *                                                                                       is
     *                                                                                       invalid
     * @throws com.turkcell.product_service.application.exceptions.ProductNotFoundException  if
     *                                                                                       product
     *                                                                                       not
     *                                                                                       found
     */
    ProductResponse getProductById(String id);

    /**
     * Get all products
     * 
     * @return list of all products
     */
    ProductListResponse getAllProducts();

    /**
     * Update an existing product
     * 
     * @param id      the product ID
     * @param request the update request
     * @return the updated product response
     * @throws com.turkcell.product_service.application.exceptions.InvalidProductIdException if
     *                                                                                       ID
     *                                                                                       is
     *                                                                                       invalid
     * @throws com.turkcell.product_service.application.exceptions.ProductNotFoundException  if
     *                                                                                       product
     *                                                                                       not
     *                                                                                       found
     */
    ProductResponse updateProduct(String id, UpdateProductRequest request);

    /**
     * Delete a product by its ID
     * 
     * @param id the product ID
     * @throws com.turkcell.product_service.application.exceptions.InvalidProductIdException if
     *                                                                                       ID
     *                                                                                       is
     *                                                                                       invalid
     * @throws com.turkcell.product_service.application.exceptions.ProductNotFoundException  if
     *                                                                                       product
     *                                                                                       not
     *                                                                                       found
     */
    void deleteProduct(String id);
}