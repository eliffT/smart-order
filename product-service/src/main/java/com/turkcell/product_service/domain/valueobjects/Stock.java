package com.turkcell.product_service.domain.valueobjects;

import java.util.Objects;

/**
 * Value Object representing product stock quantity
 */
public final class Stock {
    private final int value;

    private Stock(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.value = value;
    }

    public static Stock of(int value) {
        return new Stock(value);
    }

    public static Stock zero() {
        return new Stock(0);
    }

    public int getValue() {
        return value;
    }

    public boolean isZero() {
        return value == 0;
    }

    public boolean isPositive() {
        return value > 0;
    }

    public boolean isAvailable() {
        return value > 0;
    }

    public Stock add(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Cannot add negative quantity");
        }
        return new Stock(this.value + quantity);
    }

    public Stock subtract(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Cannot subtract negative quantity");
        }
        if (quantity > this.value) {
            throw new IllegalArgumentException("Cannot subtract more than available stock");
        }
        return new Stock(this.value - quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Stock stock = (Stock) o;
        return value == stock.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

