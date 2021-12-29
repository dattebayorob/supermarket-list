package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class FindShoppingListsServiceImplTest {
    @Mock ShoppingListRepository shoppingListRepository;
    FindShoppingListsServiceImpl findShoppingListsService;
    @BeforeEach
    void beforeEach() {
        findShoppingListsService = new FindShoppingListsServiceImpl(shoppingListRepository);
    }

    @Test
    void shouldFindListsBetweenDates() {
        var after = LocalDateTime.now();
        var before = LocalDateTime.now();
        Mockito.when(shoppingListRepository.findAll(after, before)).thenReturn(Collections.emptyList());
        Assertions.assertThat(findShoppingListsService.findAll(after, before)).isEmpty();
        Mockito.verify(shoppingListRepository).findAll(after, before);
    }
}