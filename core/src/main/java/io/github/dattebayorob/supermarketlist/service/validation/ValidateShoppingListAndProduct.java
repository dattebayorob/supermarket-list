package io.github.dattebayorob.supermarketlist.service.validation;

import io.github.dattebayorob.supermarketlist.exception.BusinessException;
import io.github.dattebayorob.supermarketlist.exception.ProductNotFoundException;
import io.github.dattebayorob.supermarketlist.exception.ShoppingListNotFoundException;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ValidateShoppingListAndProduct {
    private final ShoppingListRepository shoppingListRepository;
    private final ProductRepository productRepository;
    public void validate(final UUID shoppingListId, final UUID productId) {
        var shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new ShoppingListNotFoundException(shoppingListId));
        if ( shoppingList.isLocked() ) {
            throw new BusinessException("Shopping list is locked");
        }
        if ( !productRepository.existsById(productId) ) {
            throw new ProductNotFoundException(productId);
        }
    }
}
