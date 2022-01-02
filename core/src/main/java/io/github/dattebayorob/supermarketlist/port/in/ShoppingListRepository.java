package io.github.dattebayorob.supermarketlist.port.in;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShoppingListRepository {
    Optional<ShoppingList> findById(UUID id);
    List<ShoppingList> findAll(LocalDateTime after, LocalDateTime before);
    ShoppingList save(ShoppingList shoppingList);
}
