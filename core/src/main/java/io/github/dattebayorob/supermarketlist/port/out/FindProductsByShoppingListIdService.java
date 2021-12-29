package io.github.dattebayorob.supermarketlist.port.out;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;

import java.util.List;
import java.util.UUID;

public interface FindProductsByShoppingListIdService {
    List<ProductSelection> findProductsByShoppingListId(UUID shoppingListId);
}
