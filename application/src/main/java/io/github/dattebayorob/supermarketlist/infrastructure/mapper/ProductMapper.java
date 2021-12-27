package io.github.dattebayorob.supermarketlist.infrastructure.mapper;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.abstraction.DomainMapper;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.abstraction.ResponseMapper;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper implements
        DomainMapper<Product, ProductJpa>,
        ResponseMapper<Product, ProductResponse> {
    private final ProductCategoryMapper productCategoryMapper;
    @Override
    public Product toDomain(ProductJpa entity) {
        var domain = new Product();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setCategory(productCategoryMapper.toDomain(
            entity.getCategory()
        ));
        return domain;
    }
    @Override
    public ProductJpa toEntity(Product product) {
        var entity = new ProductJpa();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setCategory(productCategoryMapper.toEntity(
            product.getCategory()
        ));
        return entity;
    }

    @Override
    public ProductResponse toResponse(Product domain) {
        return new ProductResponse()
                .id(domain.getId().toString())
                .name(domain.getName())
                .category(productCategoryMapper.toResponse(domain.getCategory()));
    }
}
