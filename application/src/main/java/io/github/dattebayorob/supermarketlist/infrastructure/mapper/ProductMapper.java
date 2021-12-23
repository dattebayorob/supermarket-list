package io.github.dattebayorob.supermarketlist.infrastructure.mapper;

import io.github.dattebayorob.supermarketlist.common.CollectionUtil;
import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductJpa;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {
    public Product toDomain(ProductJpa entity) {
        return null;
    }
    public List<Product> toDomain(List<ProductJpa> entities) {
        return CollectionUtil.map(entities, this::toDomain);
    }
    public ProductJpa toEntity(Product product) {
        return null;
    }
    public List<ProductJpa> toEntity(List<Product> products) {
        return CollectionUtil.map(products, this::toEntity);
    }
}
