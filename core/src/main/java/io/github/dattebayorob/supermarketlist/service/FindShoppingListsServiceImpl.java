package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.domain.filter.ShoppingListFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import io.github.dattebayorob.supermarketlist.port.out.FindShoppingListsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindShoppingListsServiceImpl implements FindShoppingListsService {
    private final ShoppingListRepository shoppingListRepository;

    @Override
    public Pagination<ShoppingList> findAll(ShoppingListFilters shoppingListFilters) {
        return shoppingListRepository.findAll(shoppingListFilters);
    }
}
