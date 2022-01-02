package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CloneShoppingListServiceImplTest {
    @Mock ShoppingList shoppingList;
    @Mock ShoppingListRepository shoppingListRepository;
    @Mock  ProductSelectionRepository productSelectionRepository;
    CloneShoppingListServiceImpl cloneShoppingListService;

    @BeforeEach
    void beforeEach() {
        cloneShoppingListService = new CloneShoppingListServiceImpl(shoppingListRepository, productSelectionRepository);
    }

    @Test
    void shouldReturnShoppingListIfClonedListExists() {
        UUID toCloneShoppingListId = UUID.randomUUID();
        when(shoppingListRepository.findById(toCloneShoppingListId)).thenReturn(Optional.of(shoppingList));
        when(shoppingListRepository.save(any(ShoppingList.class))).thenReturn(shoppingList);
        when(productSelectionRepository.findByShoppingListId(toCloneShoppingListId)).thenReturn(Collections.emptyList());

        var cloned = cloneShoppingListService.clone(toCloneShoppingListId);
        assertEquals(shoppingList, cloned.get());
        InOrder inOrder = Mockito.inOrder(shoppingList, shoppingListRepository, productSelectionRepository);
        inOrder.verify(shoppingListRepository).findById(toCloneShoppingListId);
        inOrder.verify(shoppingListRepository).save(any(ShoppingList.class));
        inOrder.verify(shoppingList, never()).setEmpty(anyBoolean());
        inOrder.verify(productSelectionRepository, never()).saveAll(anyList());
    }

    @Test
    void shouldCreateSelectionsForNewShoppingListThenReturnIt() {
        UUID toCloneShoppingListId = UUID.randomUUID();

        when(shoppingListRepository.findById(toCloneShoppingListId)).thenReturn(Optional.of(shoppingList));
        when(shoppingListRepository.save(any(ShoppingList.class))).thenReturn(shoppingList);
        when(productSelectionRepository.findByShoppingListId(toCloneShoppingListId)).thenReturn(Arrays.asList(new ProductSelection()));

        var cloned = cloneShoppingListService.clone(toCloneShoppingListId);
        assertEquals(shoppingList, cloned.get());
        InOrder inOrder = Mockito.inOrder(shoppingList, shoppingListRepository, productSelectionRepository);
        inOrder.verify(shoppingListRepository).findById(toCloneShoppingListId);
        inOrder.verify(shoppingListRepository).save(any(ShoppingList.class));
        inOrder.verify(shoppingList).setEmpty(false);
        inOrder.verify(productSelectionRepository).saveAll(anyList());
    }

    @Test
    void shouldReturnEmptyOptionalIfToCloneShoppingListDoesNotExist() {
        UUID toCloneShoppingListId = UUID.randomUUID();

        when(shoppingListRepository.findById(toCloneShoppingListId)).thenReturn(Optional.empty());

        assertThat(cloneShoppingListService.clone(toCloneShoppingListId)).isEmpty();
    }
}