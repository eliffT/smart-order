package com.turkcell.product_service.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.repositories.ProductRepository;
import com.turkcell.product_service.domain.valueobjects.ProductId;
import com.turkcell.product_service.infrastructure.mapper.ProductMapper;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public Product save(Product product) {
        var entity = ProductMapper.toEntity(product);
        var savedEntity = jpaProductRepository.save(entity);
        return ProductMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return jpaProductRepository.findById(id.getValue())
                .map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        var entities = jpaProductRepository.findAll();
        return ProductMapper.toDomainList(entities);
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        var entities = jpaProductRepository.findByNameContaining(name);
        return ProductMapper.toDomainList(entities);
    }

    @Override
    public List<Product> findAvailableProducts() {
        var entities = jpaProductRepository.findByStockGreaterThan(0);
        return ProductMapper.toDomainList(entities);
    }

    @Override
    public List<Product> findOutOfStockProducts() {
        var entities = jpaProductRepository.findByStockEquals(0);
        return ProductMapper.toDomainList(entities);
    }

    @Override
    public void deleteById(ProductId id) {
        jpaProductRepository.deleteById(id.getValue());
    }

    @Override
    public boolean existsById(ProductId id) {
        return jpaProductRepository.existsById(id.getValue());
    }

    @Override
    public long count() {
        return jpaProductRepository.count();
    }
}