package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.filter.ShoppingListFilters;
import io.github.dattebayorob.supermarketlist.domain.util.Pagination;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindShoppingListsServiceImplTest {
    @Mock ShoppingListFilters filters;
    @Mock ShoppingListRepository shoppingListRepository;
    @Mock ProductRepository productRepository;
    FindShoppingListsServiceImpl findShoppingListsService;
    @BeforeEach
    void beforeEach() {
        findShoppingListsService = new FindShoppingListsServiceImpl(shoppingListRepository, productRepository);
    }

    @Test
    void shouldFindListsBetweenDates() {
        Mockito.when(shoppingListRepository.findAll(filters)).thenReturn(Pagination.empty());
        Assertions.assertThat(findShoppingListsService.findAll(filters).getContent()).isEmpty();
        Mockito.verify(shoppingListRepository).findAll(filters);
    }
}