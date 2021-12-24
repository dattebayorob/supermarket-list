package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
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
    ProductSelectionQuantityServiceImpl productSelectionQuantityService;

    @BeforeEach
    void beforeEach() {
        productSelectionQuantityService = new ProductSelectionQuantityServiceImpl(productSelectionRepository);
    }

    @Test
    void shouldDecreaseProductQuantityIfItExistsAndISMoreThenZero() {
        UUID productSelectionId = UUID.randomUUID();
        when(productSelectionRepository.getProductSelectionQuantity(productSelectionId))
                .thenReturn(Optional.of(5));

        productSelectionQuantityService.decreaseProductQuantityInProductList(productSelectionId);

        verify(productSelectionRepository).updateProductSelectionQuantity(productSelectionId, 4);
    }

    @Test
    void shouldNotDecreaseProductIfItNotExistsOrQuantityIsLessOrEqualToZero() {
        UUID productSelectionId = UUID.randomUUID();
        when(productSelectionRepository.getProductSelectionQuantity(productSelectionId))
                .thenReturn(Optional.empty());

        productSelectionQuantityService.decreaseProductQuantityInProductList(productSelectionId);
        verify(productSelectionRepository).getProductSelectionQuantity(productSelectionId);
        verifyNoMoreInteractions(productSelectionRepository);
    }

    @Test
    void shouldIncreaseProductQuantityIfITExists() {
        UUID productSelectionId = UUID.randomUUID();
        when(productSelectionRepository.getProductSelectionQuantity(productSelectionId))
                .thenReturn(Optional.of(5));

        productSelectionQuantityService.increaseProductQuantityInProductList(productSelectionId);

        verify(productSelectionRepository).updateProductSelectionQuantity(productSelectionId, 6);
    }

    @Test
    void shouldSetProductQuantityToOneIfItDoesntExist() {
        UUID productSelectionId = UUID.randomUUID();
        when(productSelectionRepository.getProductSelectionQuantity(productSelectionId))
                .thenReturn(Optional.empty());

        productSelectionQuantityService.increaseProductQuantityInProductList(productSelectionId);

        verify(productSelectionRepository).updateProductSelectionQuantity(productSelectionId, 1);
    }
}