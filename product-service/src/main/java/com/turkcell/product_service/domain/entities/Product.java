package com.turkcell.product_service.domain.entities;

import com.turkcell.product_service.domain.valueobjects.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Product Entity representing a product in the e-commerce system
 * Following DDD principles, this entity encapsulates business logic and
 * invariants
 */
public class Product {
    private ProductId id;
    private ProductName name;
    private Description description;
    private Price price;
    private Currency currency;
    private Stock stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Private constructor to enforce creation through factory methods
    private Product() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Factory method to create a new product
     */
    public static Product create(ProductName name, Description description, Price price, Currency currency,
            Stock stock) {
        Product product = new Product();
        product.id = ProductId.generate();
        product.name = name;
        product.description = description;
        product.price = price;
        product.currency = currency;
        product.stock = stock;
        return product;
    }

    /**
     * Factory method to reconstruct product from persistence
     */
    public static Product reconstruct(ProductId id, ProductName name, Description description,
            Price price, Currency currency, Stock stock,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        Product product = new Product();
        product.id = id;
        product.name = name;
        product.description = description;
        product.price = price;
        product.currency = currency;
        product.stock = stock;
        product.createdAt = createdAt;
        product.updatedAt = updatedAt;
        return product;
    }

    /**
     * Update product information
     */
    public void updateInfo(ProductName name, Description description, Price price, Currency currency) {
        if (name == null || description == null || price == null || currency == null) {
            throw new IllegalArgumentException("Product information cannot be null");
        }

        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Add stock to the product
     */
    public void addStock(int quantity) {
        this.stock = this.stock.add(quantity);
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Reduce stock when product is sold
     */
    public void reduceStock(int quantity) {
        if (!isAvailable()) {
            throw new IllegalStateException("Product is out of stock");
        }
        this.stock = this.stock.subtract(quantity);
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Check if product is available for purchase
     */
    public boolean isAvailable() {
        return stock.isAvailable();
    }

    /**
     * Check if product is out of stock
     */
    public boolean isOutOfStock() {
        return stock.isZero();
    }

    /**
     * Get the total value of current stock
     */
    public Price getTotalStockValue() {
        return Price.of(price.getValue().multiply(new java.math.BigDecimal(stock.getValue())));
    }

    // Getters
    public ProductId getId() {
        return id;
    }

    public ProductName getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public Price getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Stock getStock() {
        return stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", currency=" + currency +
                ", stock=" + stock +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}


