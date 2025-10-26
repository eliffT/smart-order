package com.turkcell.product_service.web.dtos;

import java.util.List;

/**
 * Web DTO for product list response
 */
public class ProductListWebResponse {

    private List<ProductWebResponse> products;
    private long totalCount;

    // Default constructor
    public ProductListWebResponse() {
    }

    // Constructor with products
    public ProductListWebResponse(List<ProductWebResponse> products) {
        this.products = products;
        this.totalCount = products != null ? products.size() : 0;
    }

    // Constructor with products and total count
    public ProductListWebResponse(List<ProductWebResponse> products, long totalCount) {
        this.products = products;
        this.totalCount = totalCount;
    }

    // Getters and Setters
    public List<ProductWebResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWebResponse> products) {
        this.products = products;
        this.totalCount = products != null ? products.size() : 0;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "ProductListWebResponse{" +
                "products=" + products +
                ", totalCount=" + totalCount +
                '}';
    }
}
