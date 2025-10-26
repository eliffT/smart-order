/**
 * Domain package for Product Service
 * 
 * This package contains the core business logic and domain model following
 * Domain-Driven Design (DDD) and Onion Architecture principles.
 * 
 * Package structure:
 * - entities: Core business entities (Product)
 * - valueobjects: Immutable value objects (ProductId, ProductName, etc.)
 * - repositories: Domain repository interfaces
 * - services: Domain services for complex business logic
 * - events: Domain events for event-driven architecture
 * - exceptions: Domain-specific exceptions
 * 
 * This package is the innermost layer of the Onion Architecture and should
 * not depend on any external frameworks or infrastructure concerns.
 */
package com.turkcell.product_service.domain;

