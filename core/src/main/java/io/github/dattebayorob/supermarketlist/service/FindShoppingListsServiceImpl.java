package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.domain.filter.ShoppingListFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import io.github.dattebayorob.supermarketlist.port.out.FindShoppingListsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindShoppingListsServiceImpl implements FindShoppingListsService {
    private final ShoppingListRepository shoppingListRepository;
    private final ProductRepository productRepository;

    @Override
    public Pagination<ShoppingList> findAll(ShoppingListFilters shoppingListFilters) {
        var pagination = shoppingListRepository.findAll(shoppingListFilters);
        pagination.forEach( shoppingList -> shoppingList.setEmpty(!productRepository.existsByShoppingListId(shoppingList.getId())));
        return pagination;
    }
}
