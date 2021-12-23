package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import io.github.dattebayorob.supermarketlist.port.out.FindShoppingListsService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class FindShoppingListsServiceImpl implements FindShoppingListsService {
    private final ShoppingListRepository shoppingListRepository;

    @Override
    public List<ShoppingList> findAll(LocalDateTime after, LocalDateTime before) {
        return shoppingListRepository.findAll(after, before);
    }
}
