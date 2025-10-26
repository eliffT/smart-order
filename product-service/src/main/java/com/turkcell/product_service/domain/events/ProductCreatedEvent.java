package com.turkcell.product_service.domain.events;

import com.turkcell.product_service.domain.valueobjects.ProductId;
import com.turkcell.product_service.domain.valueobjects.ProductName;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event raised when a new product is created
 */
public class ProductCreatedEvent implements DomainEvent {

    private final UUID eventId;
    private final LocalDateTime occurredOn;
    private final ProductId productId;
    private final ProductName productName;
    private final int version;

    public ProductCreatedEvent(ProductId productId, ProductName productName) {
        this.eventId = UUID.randomUUID();
        this.occurredOn = LocalDateTime.now();
        this.productId = productId;
        this.productName = productName;
        this.version = 1;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public int getVersion() {
        return version;
    }

    public ProductId getProductId() {
        return productId;
    }

    public ProductName getProductName() {
        return productName;
    }
}


