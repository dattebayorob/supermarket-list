package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.exception.ResourceNotFoundException;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindProductsByShoppingListIdServiceImplTest {
    @Mock ShoppingListRepository shoppingListRepository;
    @Mock ProductSelectionRepository productSelectionRepository;
    FindProductsByShoppingListIdServiceImpl findProductsByShoppingListIdService;

    @BeforeEach
    void beforeEach() {
        findProductsByShoppingListIdService = new FindProductsByShoppingListIdServiceImpl(shoppingListRepository, productSelectionRepository);
    }

    @Test
    void shouldThrowsShoppingListNotFoundExceptionIfListsDoesntExist() {
        UUID id = UUID.randomUUID();
        when(shoppingListRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findProductsByShoppingListIdService.findProductsByShoppingListId(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("No shopping list found for id " + id);
    }

    @Test
    void shouldReturnProductsOfShoppingListIfListExists() {
        var id = UUID.randomUUID();
        var shoppingList = new ShoppingList();
        var selections = Arrays.asList(new ProductSelection());
        when(shoppingListRepository.findById(id)).thenReturn(Optional.of(shoppingList));
        when(productSelectionRepository.findByShoppingListId(id)).thenReturn(selections);

        assertEquals(selections, findProductsByShoppingListIdService.findProductsByShoppingListId(id));
    }
}