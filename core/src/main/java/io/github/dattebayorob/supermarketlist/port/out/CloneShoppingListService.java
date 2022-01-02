package io.github.dattebayorob.supermarketlist.port.out;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;

import java.util.Optional;
import java.util.UUID;

public interface CloneShoppingListService {
    Optional<ShoppingList> clone(UUID shoppingListId);
}
