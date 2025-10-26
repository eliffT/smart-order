package com.turkcell.product_service.application.dtos;

import java.util.List;

/**
 * DTO for product list response
 */
public class ProductListResponse {

    private List<ProductResponse> products;
    private long totalCount;

    // Default constructor
    public ProductListResponse() {
    }

    // Constructor with products
    public ProductListResponse(List<ProductResponse> products) {
        this.products = products;
        this.totalCount = products != null ? products.size() : 0;
    }

    // Constructor with products and total count
    public ProductListResponse(List<ProductResponse> products, long totalCount) {
        this.products = products;
        this.totalCount = totalCount;
    }

    // Getters and Setters
    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
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
        return "ProductListResponse{" +
                "products=" + products +
                ", totalCount=" + totalCount +
                '}';
    }
}
