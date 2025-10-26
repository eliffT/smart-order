package com.turkcell.product_service.domain.events;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Base interface for domain events
 * Domain events represent something important that happened in the domain
 */
public interface DomainEvent {

    /**
     * Get the unique identifier for this event
     */
    UUID getEventId();

    /**
     * Get the timestamp when this event occurred
     */
    LocalDateTime getOccurredOn();

    /**
     * Get the version of the event
     */
    int getVersion();
}


