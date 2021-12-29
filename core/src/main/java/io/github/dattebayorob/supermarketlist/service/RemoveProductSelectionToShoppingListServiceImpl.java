package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.exception.BusinessException;
import io.github.dattebayorob.supermarketlist.exception.ProductNotFoundException;
import io.github.dattebayorob.supermarketlist.exception.ShoppingListNotFoundException;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import io.github.dattebayorob.supermarketlist.port.out.RemoveProductFromShoppingListService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class RemoveProductSelectionToShoppingListServiceImpl implements RemoveProductFromShoppingListService {
    private final ProductSelectionRepository productSelectionRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final ProductRepository productRepository;
    @Override
    public void removeProductFromShoppingList(UUID productId, UUID shoppingListId) {
        var shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new ShoppingListNotFoundException(shoppingListId));
        if ( shoppingList.isLocked() ) {
            throw  new BusinessException("Shopping list is locked");
        }
        if ( !productRepository.existsById(productId) ) {
            throw new ProductNotFoundException(productId);
        }
        productSelectionRepository.removeByProductIdAndShoppingListId(productId, shoppingListId);
    }
}
