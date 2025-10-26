package com.turkcell.product_service.application.dtos;

import java.math.BigDecimal;

/**
 * DTO for updating an existing product
 */
public class UpdateProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private String currency;
    private Integer stock;

    // Default constructor
    public UpdateProductRequest() {
    }

    // Constructor with all fields
    public UpdateProductRequest(String name, String description, BigDecimal price, String currency, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.stock = stock;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "UpdateProductRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", stock=" + stock +
                '}';
    }
}
