package com.turkcell.product_service.domain.valueobjects;

import java.util.Objects;

/**
 * Value Object representing a Product description
 */
public final class Description {
    private final String value;

    private Description(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (value.length() > 500) {
            throw new IllegalArgumentException("Description cannot exceed 500 characters");
        }
        this.value = value.trim();
    }

    public static Description of(String value) {
        return new Description(value);
    }

    public static Description empty() {
        return new Description("");
    }

    public String getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Description that = (Description) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}


