package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.port.out.CheckProductInListService;
import io.github.dattebayorob.supermarketlist.port.out.UncheckProductInListService;
import io.github.dattebayorob.supermarketlist.service.validation.ValidateShoppingListAndProduct;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class CheckOrUncheckProductInListServiceImpl implements CheckProductInListService, UncheckProductInListService {
    private final ValidateShoppingListAndProduct validateShoppingListAndProduct;
    private final ProductSelectionRepository productSelectionRepository;

    @Override
    public void checkProductInList(UUID shoppingListId, UUID productId) {
        toggleProductSelection(shoppingListId, productId, true);
    }

    @Override
    public void uncheckProductInList(UUID shoppingListId, UUID productId) {
        toggleProductSelection(shoppingListId, productId, false);
    }

    private void toggleProductSelection(UUID shoppingListId, UUID productId, boolean checked) {
        validateShoppingListAndProduct.validate(shoppingListId, productId);
        var selection = findByIdOrCreateOne(shoppingListId, productId);
        selection.setChecked(checked);
        productSelectionRepository.save(selection);
    }

    private ProductSelection findByIdOrCreateOne(UUID shoppingListId, UUID productId) {
        return productSelectionRepository.findByShoppingListIdAndProductId(shoppingListId, productId)
                .orElseGet(buildProductSelection(shoppingListId, productId));
    }

    private Supplier<ProductSelection> buildProductSelection(UUID shoppingListId, UUID productId) {
        return () -> {
            var selection = new ProductSelection();
            selection.setProduct(new Product(productId));
            selection.setShoppingList(new ShoppingList(shoppingListId));
            selection.setQuantity(1);
            return selection;
        };
    }
}
