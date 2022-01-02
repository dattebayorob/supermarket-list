package io.github.dattebayorob.supermarketlist.port.out;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.domain.ShoppingList;

import java.util.List;

public interface SaveShoppingListService {
    ShoppingList save(List<ProductSelection> selections);
}
