package io.github.dattebayorob.supermarketlist.port.in;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.filter.ProductFilters;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    List<Product> findAll(ProductFilters filters);
    List<Product> findByShoppingListId(UUID shoppingListId);
    boolean existsById(UUID id);
}
