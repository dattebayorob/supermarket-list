package io.github.dattebayorob.supermarketlist.port.in;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.domain.filter.ShoppingListFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;

import java.util.Optional;
import java.util.UUID;

public interface ShoppingListRepository {
    Optional<ShoppingList> findById(UUID id);
    Pagination<ShoppingList> findAll(ShoppingListFilters shoppingListFilters);
    ShoppingList save(ShoppingList shoppingList);
}
