package io.github.dattebayorob.supermarketlist.port.out;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;

import java.time.LocalDateTime;
import java.util.List;

public interface FindShoppingListsService {
    List<ShoppingList> findAll(LocalDateTime after, LocalDateTime before);
}
