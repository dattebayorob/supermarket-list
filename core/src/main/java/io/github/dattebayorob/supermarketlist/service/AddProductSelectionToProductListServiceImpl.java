package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.exception.BusinessException;
import io.github.dattebayorob.supermarketlist.exception.ProductNotFoundException;
import io.github.dattebayorob.supermarketlist.exception.ShoppingListNotFoundException;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import io.github.dattebayorob.supermarketlist.port.out.AddProductSelectionToProductListService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class AddProductSelectionToProductListServiceImpl implements AddProductSelectionToProductListService {
    private final ProductRepository productRepository;
    private final ShoppingListRepository shoppingListRepository;
    @Override
    public void addProductSelectionToShoppingList(UUID shoppingListId, ProductSelection productSelection) {
        var shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new ShoppingListNotFoundException(shoppingListId));
        if ( shoppingList.isLocked() ) {
            throw  new BusinessException("Shopping list is locked");
        }
        if ( !productRepository.existsById(productSelection.getProduct().getId()) ) {
            throw new ProductNotFoundException(shoppingListId);
        }
        if ( productSelection.getQuantity() == null || productSelection.getQuantity() <= 0 ) {
            productSelection.setQuantity(1);
        }
        productSelection.setShoppingList(shoppingList);

    }
}
