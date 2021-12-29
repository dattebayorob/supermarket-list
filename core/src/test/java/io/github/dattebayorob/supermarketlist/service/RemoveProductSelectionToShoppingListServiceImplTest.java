package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.exception.BusinessException;
import io.github.dattebayorob.supermarketlist.exception.ProductNotFoundException;
import io.github.dattebayorob.supermarketlist.exception.ShoppingListNotFoundException;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class RemoveProductSelectionToShoppingListServiceImplTest {
    @Mock ProductSelectionRepository productSelectionRepository;
    @Mock ShoppingListRepository shoppingListRepository;
    @Mock ProductRepository productRepository;
    RemoveProductSelectionToShoppingListServiceImpl removeProductSelectionToShoppingListService;
    @BeforeEach
    void beforeEach() {
        removeProductSelectionToShoppingListService = new RemoveProductSelectionToShoppingListServiceImpl(
            productSelectionRepository,
            shoppingListRepository,
            productRepository
        );
    }
    @Test
    void shouldThrowsShoppingListNotFoundExceptionIfListDoesntExist() {
        var productId = UUID.randomUUID();
        var listId = UUID.randomUUID();
        assertThatThrownBy(
            () -> removeProductSelectionToShoppingListService.removeProductFromShoppingList(productId, listId)
        ).isInstanceOf(ShoppingListNotFoundException.class);
    }
    @Test
    void shouldThrowsBusinessExceptionIfListIsLocked() {
        var productId = UUID.randomUUID();
        var listId = UUID.randomUUID();
        var list = new ShoppingList(listId);
        list.setLocked(true);
        Mockito.when(shoppingListRepository.findById(listId)).thenReturn(Optional.of(list));
        assertThatThrownBy(
                () -> removeProductSelectionToShoppingListService.removeProductFromShoppingList(productId, listId)
        ).isInstanceOf(BusinessException.class).hasMessage("Shopping list is locked");
    }
    @Test
    void shouldThrowsProductNotFoundExceptionIfProductDoesntExist() {
        var productId = UUID.randomUUID();
        var listId = UUID.randomUUID();
        var list = new ShoppingList(listId);
        Mockito.when(shoppingListRepository.findById(listId)).thenReturn(Optional.of(list));
        Mockito.when(productRepository.existsById(productId)).thenReturn(false);
        assertThatThrownBy(
                () -> removeProductSelectionToShoppingListService.removeProductFromShoppingList(productId, listId)
        ).isInstanceOf(ProductNotFoundException.class);
    }
    @Test
    void shouldRemoveSelectionsOfProduct() {
        var productId = UUID.randomUUID();
        var listId = UUID.randomUUID();
        var list = new ShoppingList(listId);
        Mockito.when(shoppingListRepository.findById(listId)).thenReturn(Optional.of(list));
        Mockito.when(productRepository.existsById(productId)).thenReturn(true);
        assertThatCode(
                () -> removeProductSelectionToShoppingListService.removeProductFromShoppingList(productId, listId)
        ).doesNotThrowAnyException();
        Mockito.verify(productSelectionRepository).removeByProductIdAndShoppingListId(productId, listId);
    }
}