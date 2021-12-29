package io.github.dattebayorob.supermarketlist.port.out;

import java.util.UUID;

public interface RemoveProductFromShoppingListService {
    void removeProductFromShoppingList(UUID productId, UUID shoppingListId);
}
