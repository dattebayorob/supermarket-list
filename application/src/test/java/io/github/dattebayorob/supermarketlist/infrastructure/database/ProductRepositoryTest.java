package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository.ProductJpaRepository;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.specification.FindProductsByFiltersSpecification;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.PaginationMapper;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @Mock Specification<ProductJpa> specification;
    @Mock Pageable pageable;

    @Mock ProductJpaRepository productJpaRepository;
    @Mock ProductMapper productMapper;
    @Mock FindProductsByFiltersSpecification findProductsByFiltersSpecification;
    @Mock PaginationMapper paginationMapper;
    ProductRepositoryImpl productRepository;

    @BeforeEach
    void beforeEach() {
        productRepository = new ProductRepositoryImpl(productJpaRepository, productMapper, findProductsByFiltersSpecification, paginationMapper);
    }

    @Test
    void shouldVerifyIfProductExistsbyId() {
        var uuid = UUID.randomUUID();
        when(productJpaRepository.existsById(uuid)).thenReturn(true);
        assertTrue(productRepository.existsById(uuid));
    }

    @Test
    void shouldVerifyIfProductExistsByShoppingListId() {
        var uuid = UUID.randomUUID();
        when(productJpaRepository.existsByShoppingListId(uuid)).thenReturn(true);
        assertTrue(productRepository.existsByShoppingListId(uuid));
    }

    @Test
    void shouldFindProductsByShoppingListId() {
        var uuid = UUID.randomUUID();
        var products = List.of(new ProductJpa());
        var domain = List.of(new Product());
        when(productJpaRepository.findByShoppingListId(uuid)).thenReturn(products);
        when(productMapper.toDomain(products)).thenReturn(domain);
        assertEquals(domain, productRepository.findByShoppingListId(uuid));
    }

    @Test
    void shouldFindAllBySpecification() {
        var filters = new ProductFilters("Product", null);
        var page = Page.<ProductJpa>empty();
        when(findProductsByFiltersSpecification.findProductsByFilters(filters)).thenReturn(specification);
        when(paginationMapper.toPageable(filters)).thenReturn(pageable);
        when(productJpaRepository.findAll(specification, pageable)).thenReturn(page);
        when(paginationMapper.toPagination(page)).thenReturn(Pagination.empty());

        assertNotNull(productRepository.findAll(filters));
    }
}
