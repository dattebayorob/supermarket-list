package io.github.dattebayorob.supermarketlist.port.in;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    Pagination<Product> findAll(ProductFilters filters);
    List<Product> findByShoppingListId(UUID shoppingListId);
    boolean existsByShoppingListId(UUID shoppingListId);
    boolean existsById(UUID id);
}
