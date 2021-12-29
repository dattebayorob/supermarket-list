package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ProductJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository.ProductJpaRepository;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.specification.FindProductsByFiltersSpecification;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductMapper;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository{
    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;
    private final FindProductsByFiltersSpecification findProductsByFiltersSpecification;
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll(ProductFilters filters) {
        Specification<ProductJpa> specification = findProductsByFiltersSpecification.findProductsByFilters(filters);
        return productMapper.toDomain(productJpaRepository.findAll(specification));
    }

    @Override
    public boolean existsById(UUID id) {
        return productJpaRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByShoppingListId(UUID shoppingListId) {
        return productMapper.toDomain(productJpaRepository.findByShoppingListId(shoppingListId));
    }
}
