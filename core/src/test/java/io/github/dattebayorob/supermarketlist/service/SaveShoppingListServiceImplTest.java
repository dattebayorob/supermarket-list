package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveShoppingListServiceImplTest {
    @Mock ProductSelection productSelection;
    @Mock ShoppingListRepository shoppingListRepository;
    @Mock ProductSelectionRepository productSelectionRepository;
    SaveShoppingListServiceImpl saveShoppingListService;

    @BeforeEach
    void beforeEach() {
        saveShoppingListService = new SaveShoppingListServiceImpl(shoppingListRepository, productSelectionRepository);
    }

    @Test
    void shouldSaveShoppingListWithProductSelections() {
        when(shoppingListRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(productSelectionRepository.saveAll(anyList())).thenAnswer(invocation -> invocation.getArgument(0));
        var shoppingList = saveShoppingListService.save(Arrays.asList(productSelection));
        assertNotNull(shoppingList);
        assertFalse(shoppingList.isEmpty());
        verify(productSelection).setShoppingList(any(ShoppingList.class));
    }

    @Test
    void shouldSaveEmptyShoppingList() {
        when(shoppingListRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        var shoppingList = saveShoppingListService.save(Collections.emptyList());
        assertNotNull(shoppingList);
        assertTrue(shoppingList.isEmpty());
    }

}