package io.github.dattebayorob.supermarketlist.service.validation;

import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.exception.BusinessException;
import io.github.dattebayorob.supermarketlist.exception.ProductNotFoundException;
import io.github.dattebayorob.supermarketlist.exception.ShoppingListNotFoundException;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateShoppingListAndProductTest {
    static final UUID shoppingListId = UUID.randomUUID();
    static final UUID productId = UUID.randomUUID();

    @Mock ShoppingList shoppingList;
    @Mock ShoppingListRepository shoppingListRepository;
    @Mock ProductRepository productRepository;
    ValidateShoppingListAndProduct validation;

    @BeforeEach
    void beforeEach() {
        validation = new ValidateShoppingListAndProduct(shoppingListRepository, productRepository);
    }

    @Test
    void shouldThrowShoppingListNotFoundExceptionIfShoppingListDoesNotExist() {
        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> validation.validate(shoppingListId, productId))
                .isInstanceOf(ShoppingListNotFoundException.class)
                .hasMessage("No shopping list found for id "+shoppingListId);
    }

    @Test
    void shouldThrowBusinessExceptionIfShoppingListIsLocked() {
        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.of(shoppingList));
        when(shoppingList.isLocked()).thenReturn(true);
        assertThatThrownBy(() -> validation.validate(shoppingListId, productId))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Shopping list is locked");
    }

    @Test
    void shouldThrowProductNotFoundExceptionIfProductDoesNotExist() {
        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.of(shoppingList));
        when(productRepository.existsById(productId)).thenReturn(false);
        assertThatThrownBy(() -> validation.validate(shoppingListId, productId))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("No product found for id "+productId);
    }

    @Test
    void shouldDoesNothingIfShoppingListExistsAndIsNotLockedAndProductExists() {
        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.of(shoppingList));
        when(productRepository.existsById(productId)).thenReturn(true);
        assertThatCode(() -> validation.validate(shoppingListId, productId))
                .doesNotThrowAnyException();
    }
}