package io.github.dattebayorob.supermarketlist.port.out;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.domain.filter.ShoppingListFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;

public interface FindShoppingListsService {
    Pagination<ShoppingList> findAll(ShoppingListFilters shoppingListFilters);
}
