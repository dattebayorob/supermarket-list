package io.github.dattebayorob.supermarketlist.infrastructure.mapper;

import io.github.dattebayorob.supermarketlist.common.CollectionUtil;
import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductCategoryJpa;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductCategoryMapper {
    public ProductCategory toDomain(ProductCategoryJpa entity) {
        var domain = new ProductCategory();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        return domain;
    }
    public List<ProductCategory> toDomain(List<ProductCategoryJpa> entities) {
        return CollectionUtil.map(entities, this::toDomain);
    }
    public ProductCategoryJpa toEntity(ProductCategory domain) {
        var entity = new ProductCategoryJpa();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        return entity;
    }
    public List<ProductCategoryJpa> toEntity(List<ProductCategory> domains) {
        return CollectionUtil.map(domains, this::toEntity);
    }
    public ProductCategoryResponse toResponse(ProductCategory domain) {
        return new ProductCategoryResponse().id(domain.getId().toString()).name(domain.getName());
    }
    public List<ProductCategoryResponse> toResponse(List<ProductCategory> domain) {
        return CollectionUtil.map(domain, this::toResponse);
    }
}
