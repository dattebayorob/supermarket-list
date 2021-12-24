package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ShoppingListRepositoryImpl implements ShoppingListRepository {
    @Override
    public Optional<ShoppingList> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<ShoppingList> findAll(LocalDateTime after, LocalDateTime before) {
        return Collections.emptyList();
    }
}
