package com.turkcell.product_service.web.mappers;

import com.turkcell.product_service.application.dtos.*;
import com.turkcell.product_service.web.dtos.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between web DTOs and application DTOs
 */
public class ProductWebMapper {

    /**
     * Converts CreateProductWebRequest to CreateProductRequest
     */
    public static CreateProductRequest toApplicationRequest(CreateProductWebRequest webRequest) {
        if (webRequest == null) {
            return null;
        }

        return new CreateProductRequest(
                webRequest.getName(),
                webRequest.getDescription(),
                webRequest.getPrice(),
                webRequest.getCurrency(),
                webRequest.getStock());
    }

    /**
     * Converts UpdateProductWebRequest to UpdateProductRequest
     */
    public static UpdateProductRequest toApplicationRequest(UpdateProductWebRequest webRequest) {
        if (webRequest == null) {
            return null;
        }

        return new UpdateProductRequest(
                webRequest.getName(),
                webRequest.getDescription(),
                webRequest.getPrice(),
                webRequest.getCurrency(),
                webRequest.getStock());
    }

    /**
     * Converts ProductResponse to ProductWebResponse
     */
    public static ProductWebResponse toWebResponse(ProductResponse appResponse) {
        if (appResponse == null) {
            return null;
        }

        return new ProductWebResponse(
                appResponse.getId(),
                appResponse.getName(),
                appResponse.getDescription(),
                appResponse.getPrice(),
                appResponse.getCurrency(),
                appResponse.getStock(),
                appResponse.getCreatedAt(),
                appResponse.getUpdatedAt());
    }

    /**
     * Converts ProductListResponse to ProductListWebResponse
     */
    public static ProductListWebResponse toWebResponse(ProductListResponse appResponse) {
        if (appResponse == null) {
            return new ProductListWebResponse();
        }

        List<ProductWebResponse> webProducts = appResponse.getProducts().stream()
                .map(ProductWebMapper::toWebResponse)
                .collect(Collectors.toList());

        return new ProductListWebResponse(webProducts, appResponse.getTotalCount());
    }
}
