package io.github.dattebayorob.supermarketlist.infrastructure.mapper;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductCategoryJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductJpa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

class ProductMapperTest {
    ProductMapper productMapper;
    @BeforeEach
    void beforeEach() {
        productMapper = new ProductMapper(new ProductCategoryMapper());
    }

    @Test
    void shouldMapDomainToEntity() {
        var domain = getDomain();
        var entity = productMapper.toEntity(domain);
        Assertions.assertThat(domain)
                .usingRecursiveComparison()
                .comparingOnlyFields("id", "name", "category.id")
                .isEqualTo(entity);
    }

    @Test
    void shouldMapAListOfDomainsToEntities() {
        var domain = Arrays.asList(getDomain());
        var entities = productMapper.toEntity(domain);
        Assertions.assertThat(domain)
                .usingRecursiveComparison()
                .comparingOnlyFields("id", "name", "category.id")
                .isEqualTo(entities);
    }

    @Test
    void shouldMapEntityToDomain() {
        var entity = getEntity();
        var domain = productMapper.toDomain(entity);
        Assertions.assertThat(entity)
                .usingRecursiveComparison()
                .comparingOnlyFields("id", "name", "category.id")
                .isEqualTo(domain);
    }

    @Test
    void shouldMapListOfEntitiesToDomain() {
        var entity = Arrays.asList(getEntity());
        var domain = productMapper.toDomain(entity);
        Assertions.assertThat(entity)
                .usingRecursiveComparison()
                .comparingOnlyFields("id", "name", "category.id")
                .isEqualTo(domain);
    }

    @Test
    void shouldMapDomainToResponse() {
        var domain = getDomain();
        var response = productMapper.toResponse(domain);
        Assertions.assertThat(response)
                .usingRecursiveComparison()
                .comparingOnlyFields("id", "name", "category.id")
                .isEqualTo(response);
    }

    @Test
    void shouldMapaListOfDomainObjectsToResponses() {
        var domain = Arrays.asList(getDomain());
        var response = productMapper.toResponse(domain);
        Assertions.assertThat(response)
                .usingRecursiveComparison()
                .comparingOnlyFields("id", "name", "category.id")
                .isEqualTo(response);
    }


    ProductJpa getEntity() {
        var entity = new ProductJpa();
        var category = new ProductCategoryJpa();
        category.setId(UUID.randomUUID());
        entity.setId(UUID.randomUUID());
        entity.setName("Product");
        entity.setCategory(category);
        return entity;
    }

    Product getDomain() {
        var domain = new Product();
        var category = new ProductCategory();
        category.setId(UUID.randomUUID());
        category.setName("Category");
        domain.setId(UUID.randomUUID());
        domain.setName("Name");
        domain.setCategory(category);
        return domain;
    }
}