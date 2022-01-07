package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.service.validation.ValidateShoppingListAndProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductSelectionQuantityServiceImplTest {
    @Mock ProductSelectionRepository productSelectionRepository;
    @Mock ValidateShoppingListAndProduct validateShoppingListAndProduct;
    ProductSelectionQuantityServiceImpl productSelectionQuantityService;

    @BeforeEach
    void beforeEach() {
        productSelectionQuantityService = new ProductSelectionQuantityServiceImpl(productSelectionRepository, validateShoppingListAndProduct);
    }

    @Test
    void shouldDecreaseProductQuantityIfItExistsAndISMoreThenZero() {
        UUID productId = UUID.randomUUID();
        UUID shoppingListId = UUID.randomUUID();
        when(productSelectionRepository.getProductSelectionQuantity(productId, shoppingListId))
                .thenReturn(Optional.of(5));

        productSelectionQuantityService.decreaseProductQuantityInProductList(productId, shoppingListId);

        verify(productSelectionRepository).updateProductSelectionQuantity(productId, shoppingListId, 4);
    }

    @Test
    void shouldNotDecreaseProductIfItNotExistsOrQuantityIsLessOrEqualToZero() {
        UUID productId = UUID.randomUUID();
        UUID shoppingListId = UUID.randomUUID();
        when(productSelectionRepository.getProductSelectionQuantity(productId, shoppingListId))
                .thenReturn(Optional.empty());

        productSelectionQuantityService.decreaseProductQuantityInProductList(productId, shoppingListId);
        verify(productSelectionRepository).getProductSelectionQuantity(productId, shoppingListId);
        verifyNoMoreInteractions(productSelectionRepository);
    }

    @Test
    void shouldIncreaseProductQuantityIfITExists() {
        UUID productId = UUID.randomUUID();
        UUID shoppingListId = UUID.randomUUID();

        when(productSelectionRepository.getProductSelectionQuantity(productId, shoppingListId))
                .thenReturn(Optional.of(5));

        productSelectionQuantityService.increaseProductQuantityInProductList(productId, shoppingListId);

        verify(productSelectionRepository).updateProductSelectionQuantity(productId, shoppingListId, 6);
    }

    @Test
    void shouldSetProductQuantityToOneIfItDoesntExist() {
        UUID productId = UUID.randomUUID();
        UUID shoppingListId = UUID.randomUUID();
        when(productSelectionRepository.getProductSelectionQuantity(productId, shoppingListId))
                .thenReturn(Optional.empty());

        productSelectionQuantityService.increaseProductQuantityInProductList(productId, shoppingListId);

        verify(productSelectionRepository).updateProductSelectionQuantity(productId, shoppingListId, 1);
    }
}