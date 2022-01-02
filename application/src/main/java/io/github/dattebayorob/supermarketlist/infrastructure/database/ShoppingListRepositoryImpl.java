package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository.ShoppingListJpaRepository;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ShoppingListMapper;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ShoppingListRepositoryImpl implements ShoppingListRepository {
    private final ShoppingListJpaRepository shoppingListJpaRepository;
    private final ShoppingListMapper shoppingListMapper;
    @Override
    public Optional<ShoppingList> findById(UUID id) {
        return shoppingListJpaRepository.findById(id).map(shoppingListMapper::toDomain);
    }

    @Override
    public List<ShoppingList> findAll(LocalDateTime after, LocalDateTime before) {
        return shoppingListMapper.toDomain(
            shoppingListJpaRepository.findByCreatedAtBetweenOrderByCreatedAtDesc(after, before)
        );
    }

    @Override
    public ShoppingList save(ShoppingList shoppingList) {
        return shoppingListMapper.toDomain(shoppingListJpaRepository.save(
                shoppingListMapper.toEntity(shoppingList)
        ));
    }
}
