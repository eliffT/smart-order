package com.turkcell.product_service.domain.repositories;

import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.valueobjects.ProductId;

import java.util.List;
import java.util.Optional;

/**
 * Domain repository interface for Product aggregate
 * This interface belongs to the domain layer and defines the contract for
 * product persistence
 */
public interface ProductRepository {

    /**
     * Save a product (create or update)
     * 
     * @param product the product to save
     * @return the saved product
     */
    Product save(Product product);

    /**
     * Find a product by its unique identifier
     * 
     * @param id the product identifier
     * @return Optional containing the product if found, empty otherwise
     */
    Optional<Product> findById(ProductId id);

    /**
     * Find all products
     * 
     * @return list of all products
     */
    List<Product> findAll();

    /**
     * Find products by name (partial match)
     * 
     * @param name the name to search for
     * @return list of products matching the name
     */
    List<Product> findByNameContaining(String name);

    /**
     * Find products that are in stock
     * 
     * @return list of available products
     */
    List<Product> findAvailableProducts();

    /**
     * Find products that are out of stock
     * 
     * @return list of out-of-stock products
     */
    List<Product> findOutOfStockProducts();

    /**
     * Delete a product by its identifier
     * 
     * @param id the product identifier
     */
    void deleteById(ProductId id);

    /**
     * Check if a product exists by its identifier
     * 
     * @param id the product identifier
     * @return true if product exists, false otherwise
     */
    boolean existsById(ProductId id);

    /**
     * Count total number of products
     * 
     * @return total product count
     */
    long count();
}


