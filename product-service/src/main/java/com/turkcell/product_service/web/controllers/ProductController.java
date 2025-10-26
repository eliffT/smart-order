package com.turkcell.product_service.web.controllers;

import com.turkcell.product_service.application.ports.ProductServicePort;
import com.turkcell.product_service.web.dtos.*;
import com.turkcell.product_service.web.mappers.ProductWebMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Product operations
 */
@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductServicePort productService;

    public ProductController(ProductServicePort productService) {
        this.productService = productService;
    }

    /**
     * Create a new product
     * POST /api/v1/products
     */
    @PostMapping
    public ResponseEntity<ProductWebResponse> createProduct(@Valid @RequestBody CreateProductWebRequest request) {
        logger.info("Creating new product: {}", request.getName());

        var appRequest = ProductWebMapper.toApplicationRequest(request);
        var appResponse = productService.createProduct(appRequest);
        var webResponse = ProductWebMapper.toWebResponse(appResponse);

        logger.info("Product created successfully with ID: {}", webResponse.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(webResponse);
    }

    /**
     * Get a product by ID
     * GET /api/v1/products/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductWebResponse> getProductById(@PathVariable String id) {
        logger.info("Getting product by ID: {}", id);

        var appResponse = productService.getProductById(id);
        var webResponse = ProductWebMapper.toWebResponse(appResponse);

        logger.info("Product found: {}", webResponse.getName());
        return ResponseEntity.ok(webResponse);
    }

    /**
     * Get all products
     * GET /api/v1/products
     */
    @GetMapping
    public ResponseEntity<ProductListWebResponse> getAllProducts() {
        logger.info("Getting all products");

        var appResponse = productService.getAllProducts();
        var webResponse = ProductWebMapper.toWebResponse(appResponse);

        logger.info("Found {} products", webResponse.getTotalCount());
        return ResponseEntity.ok(webResponse);
    }

    /**
     * Update a product
     * PUT /api/v1/products/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductWebResponse> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody UpdateProductWebRequest request) {

        logger.info("Updating product with ID: {}", id);

        var appRequest = ProductWebMapper.toApplicationRequest(request);
        var appResponse = productService.updateProduct(id, appRequest);
        var webResponse = ProductWebMapper.toWebResponse(appResponse);

        logger.info("Product updated successfully: {}", webResponse.getName());
        return ResponseEntity.ok(webResponse);
    }

    /**
     * Delete a product
     * DELETE /api/v1/products/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        logger.info("Deleting product with ID: {}", id);

        productService.deleteProduct(id);

        logger.info("Product deleted successfully");
        return ResponseEntity.noContent().build();
    }

    /**
     * Health check endpoint
     * GET /api/v1/products/health
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Product Service is running");
    }
}
