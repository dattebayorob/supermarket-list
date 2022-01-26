package io.github.dattebayorob.supermarketlist.infrastructure.database;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.domain.filter.ShoppingListFilters;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.ShoppingListJpa;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.repository.ShoppingListJpaRepository;
import io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.specification.FindShoppingListsByFiltersSpecification;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.PaginationMapper;
import io.github.dattebayorob.supermarketlist.infrastructure.mapper.ShoppingListMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingListRepositoryImplTest {
    @Mock ShoppingListJpa entity;
    @Mock ShoppingList domain;
    @Mock ShoppingListJpaRepository shoppingListJpaRepository;
    @Mock ShoppingListMapper shoppingListMapper;
    @Mock FindShoppingListsByFiltersSpecification specification;
    @Mock PaginationMapper paginationMapper;
    ShoppingListRepositoryImpl shoppingListRepository;

    @BeforeEach
    void beforeEach() {
        shoppingListRepository = new ShoppingListRepositoryImpl(shoppingListJpaRepository, shoppingListMapper, specification, paginationMapper);
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
        ShoppingListFilters filters = new ShoppingListFilters(after, before, null, null, null);
        when(specification.findShoppingListsByFilters(filters)).thenReturn(mock(Specification.class));
        when(shoppingListJpaRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(Page.empty());
        assertNotNull(shoppingListRepository.findAll(filters));
        verify(shoppingListJpaRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    void shouldSaveShoppingList() {
        when(shoppingListMapper.toEntity(domain)).thenReturn(entity);
        when(shoppingListJpaRepository.save(entity)).thenAnswer(invocation -> invocation.getArgument(0));
        when(shoppingListMapper.toDomain(entity)).thenReturn(domain);

        assertEquals(domain, shoppingListRepository.save(domain));
    }
}
