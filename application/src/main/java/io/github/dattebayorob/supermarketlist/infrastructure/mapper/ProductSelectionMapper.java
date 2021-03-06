package io.github.dattebayorob.supermarketlist.infrastructure.mapper;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductSelectionId;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductSelectionJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.projection.ProductSelectionProjection;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.abstraction.DomainMapper;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.abstraction.ResponseMapper;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductSelectionRequest;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductSelectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.lang.Boolean.TRUE;

@Component
@RequiredArgsConstructor
public class ProductSelectionMapper implements
        DomainMapper<ProductSelection, ProductSelectionJpa>,
        ResponseMapper<ProductSelection, ProductSelectionResponse> {
    private final ProductCategoryMapper productCategoryMapper;
    @Override
    public ProductSelectionJpa toEntity(ProductSelection domain) {
        var entity = new ProductSelectionJpa();
        entity.setId(new ProductSelectionId(
            domain.getProduct().getId(), domain.getShoppingList().getId()
        ));
        entity.setQuantity(domain.getQuantity());
        entity.setChecked(domain.isChecked());
        return entity;
    }

    @Override
    public ProductSelection toDomain(ProductSelectionJpa entity) {
        var domain = new ProductSelection();
        var productId = entity.getId().getProductId();
        var shoppingListId = entity.getId().getShoppingListId();
        domain.setProduct(new Product(productId));
        domain.setShoppingList(new ShoppingList(shoppingListId));
        domain.setQuantity(entity.getQuantity());
        domain.setChecked(entity.isChecked());
        return domain;
    }

    public ProductSelection toDomain(ProductSelectionProjection projection) {
        var domain = new ProductSelection();
        domain.setProduct(projectionToProduct(projection));
        domain.setShoppingList(new ShoppingList(projection.getShoppingListId()));
        domain.setQuantity(projection.getQuantity());
        domain.setChecked(TRUE.equals(projection.getChecked()));
        return domain;
    }

    public ProductSelection toDomain(ProductSelectionRequest request) {
        var domain = new ProductSelection();
        domain.setProduct(new Product(UUID.fromString(request.getId())));
        domain.setQuantity(request.getQuantity());
        domain.setChecked(TRUE.equals(request.isChecked()));
        return domain;
    }

    private Product projectionToProduct(ProductSelectionProjection projection) {
        var product = new Product();
        var category = new ProductCategory();
        category.setId(projection.getProductCategoryId());
        category.setName(projection.getProductCategoryName());
        product.setId(projection.getProductId());
        product.setName(projection.getProductName());
        product.setCategory(category);
        return product;
    }

    @Override
    public ProductSelectionResponse toResponse(ProductSelection domain) {
        return new ProductSelectionResponse()
                .id(domain.getProduct().getId().toString())
                .category(productCategoryMapper.toResponse(domain.getProduct().getCategory()))
                .shoppingListId(domain.getShoppingList().getId().toString())
                .quantity(domain.getQuantity())
                .checked(domain.isChecked())
                .name(domain.getProduct().getName());
    }
}
