package com.turkcell.product_service.domain.events;

import com.turkcell.product_service.domain.valueobjects.ProductId;
import com.turkcell.product_service.domain.valueobjects.Stock;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain event raised when product stock is reduced
 */
public class ProductStockReducedEvent implements DomainEvent {

    private final UUID eventId;
    private final LocalDateTime occurredOn;
    private final ProductId productId;
    private final Stock reducedAmount;
    private final Stock remainingStock;
    private final int version;

    public ProductStockReducedEvent(ProductId productId, Stock reducedAmount, Stock remainingStock) {
        this.eventId = UUID.randomUUID();
        this.occurredOn = LocalDateTime.now();
        this.productId = productId;
        this.reducedAmount = reducedAmount;
        this.remainingStock = remainingStock;
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

    public Stock getReducedAmount() {
        return reducedAmount;
    }

    public Stock getRemainingStock() {
        return remainingStock;
    }
}


