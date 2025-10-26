package com.turkcell.product_service.domain.valueobjects;

import java.util.Objects;

/**
 * Value Object representing a currency code (ISO 4217)
 */
public final class Currency {
    private final String value;

    private Currency(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
        String trimmedValue = value.trim().toUpperCase();
        if (trimmedValue.length() != 3) {
            throw new IllegalArgumentException("Currency must be a 3-letter ISO 4217 code");
        }
        this.value = trimmedValue;
    }

    public static Currency of(String value) {
        return new Currency(value);
    }

    public static Currency TRY() {
        return new Currency("TRY");
    }

    public static Currency USD() {
        return new Currency("USD");
    }

    public static Currency EUR() {
        return new Currency("EUR");
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Currency currency = (Currency) o;
        return Objects.equals(value, currency.value);
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


