package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ShoppingListJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository.ShoppingListJpaRepository;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ShoppingListMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingListRepositoryImplTest {
    @Mock ShoppingListJpa entity;
    @Mock ShoppingList domain;
    @Mock ShoppingListJpaRepository shoppingListJpaRepository;
    @Mock ShoppingListMapper shoppingListMapper;
    ShoppingListRepositoryImpl shoppingListRepository;

    @BeforeEach
    void beforeEach() {
        shoppingListRepository = new ShoppingListRepositoryImpl(shoppingListJpaRepository, shoppingListMapper);
    }

    @Test
    void shouldFindById() {
        UUID id = UUID.randomUUID();
        when(shoppingListJpaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(shoppingListMapper.toDomain(entity)).thenReturn(domain);

        assertEquals(domain, shoppingListRepository.findById(id).get());
    }

    @Test
    void shouldFindAllByPeriodOfDates() {
        LocalDateTime after = LocalDateTime.now();
        LocalDateTime before = LocalDateTime.now();

        when(shoppingListJpaRepository.findByCreatedAtBetweenOrderByCreatedAtDesc(after, before)).thenReturn(Collections.emptyList());
        assertNotNull(shoppingListRepository.findAll(after, before));
        verify(shoppingListJpaRepository).findByCreatedAtBetweenOrderByCreatedAtDesc(after, before);
    }

    @Test
    void shouldSaveShoppingList() {
        when(shoppingListMapper.toEntity(domain)).thenReturn(entity);
        when(shoppingListJpaRepository.save(entity)).thenAnswer(invocation -> invocation.getArgument(0));
        when(shoppingListMapper.toDomain(entity)).thenReturn(domain);

        assertEquals(domain, shoppingListRepository.save(domain));
    }
}
