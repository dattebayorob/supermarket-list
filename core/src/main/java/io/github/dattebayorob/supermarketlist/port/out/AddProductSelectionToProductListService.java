package io.github.dattebayorob.supermarketlist.port.out;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;

import java.util.Optional;
import java.util.UUID;

public interface AddProductSelectionToProductListService {
    void addProductSelectionToShoppingList(UUID shoppingListId, ProductSelection productSelection);
}
