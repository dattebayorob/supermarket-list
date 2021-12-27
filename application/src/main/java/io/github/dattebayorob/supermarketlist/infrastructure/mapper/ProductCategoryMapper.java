package io.github.dattebayorob.supermarketlist.infrastructure.mapper;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductCategoryJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.abstraction.DomainMapper;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.abstraction.ResponseMapper;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryMapper implements
        DomainMapper<ProductCategory, ProductCategoryJpa>,
        ResponseMapper<ProductCategory, ProductCategoryResponse> {
    @Override
    public ProductCategory toDomain(ProductCategoryJpa entity) {
        var domain = new ProductCategory();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        return domain;
    }
    public ProductCategory toDomain(ProductCategoryRequest request) {
        var domain = new ProductCategory();
        domain.setName(request.getName());
        return domain;
    }
    @Override
    public ProductCategoryJpa toEntity(ProductCategory domain) {
        var entity = new ProductCategoryJpa();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        return entity;
    }
    @Override
    public ProductCategoryResponse toResponse(ProductCategory domain) {
        return new ProductCategoryResponse().id(domain.getId().toString()).name(domain.getName());
    }
}
