package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository.ProductJpaRepository;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.specification.FindProductsByFiltersSpecification;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.PaginationMapper;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ProductMapper;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository{
    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;
    private final FindProductsByFiltersSpecification findProductsByFiltersSpecification;
    private final PaginationMapper paginationMapper;
    @Override
    public Pagination<Product> findAll(ProductFilters filters) {
        var specification = findProductsByFiltersSpecification.findProductsByFilters(filters);
        var pageable = paginationMapper.toPageable(filters);
        var page = productJpaRepository.findAll(specification, pageable).map(productMapper::toDomain);
        return paginationMapper.toPagination(page);
    }

    @Override
    public boolean existsById(UUID id) {
        return productJpaRepository.existsById(id);
    }

    @Override
    public List<Product> findByShoppingListId(UUID shoppingListId) {
        return productMapper.toDomain(productJpaRepository.findByShoppingListId(shoppingListId));
    }

    @Override
    public boolean existsByShoppingListId(UUID shoppingListId) {
        return productJpaRepository.existsByShoppingListId(shoppingListId);
    }
}
