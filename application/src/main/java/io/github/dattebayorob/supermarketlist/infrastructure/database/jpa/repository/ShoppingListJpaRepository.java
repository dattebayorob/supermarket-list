package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository;

import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ShoppingListJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ShoppingListJpaRepository extends JpaRepository<ShoppingListJpa, UUID> {
    List<ShoppingListJpa> findByCreatedAtBetweenOrderByCreatedAtDesc(LocalDateTime after, LocalDateTime before);
}
