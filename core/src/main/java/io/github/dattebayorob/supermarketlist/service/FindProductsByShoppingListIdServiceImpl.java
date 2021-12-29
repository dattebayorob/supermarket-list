package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.exception.ShoppingListNotFoundException;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import io.github.dattebayorob.supermarketlist.port.out.FindProductsByShoppingListIdService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class FindProductsByShoppingListIdServiceImpl implements FindProductsByShoppingListIdService {
    private final ShoppingListRepository shoppingListRepository;
    private final ProductSelectionRepository productSelectionRepository;
    @Override
    public List<ProductSelection> findProductsByShoppingListId(UUID shoppingListId) {
        var shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new ShoppingListNotFoundException(shoppingListId));
        return productSelectionRepository.findByShoppingListId(shoppingListId);
    }
}
