package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.service.validation.ValidateShoppingListAndProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckOrUncheckProductInListServiceImplTest {
    static final UUID shoppingListId = UUID.randomUUID();
    static final UUID productId = UUID.randomUUID();
    @Mock ProductSelection productSelection;
    @Mock ValidateShoppingListAndProduct validateShoppingListAndProduct;
    @Mock ProductSelectionRepository productSelectionRepository;
    CheckOrUncheckProductInListServiceImpl checkOrUncheckProductInListService;

    @BeforeEach
    void beforeEach() {
        checkOrUncheckProductInListService = new CheckOrUncheckProductInListServiceImpl(validateShoppingListAndProduct, productSelectionRepository);
    }

    @Test
    void shouldToggleCheckedAsTrue() {
        when(productSelectionRepository.findByShoppingListIdAndProductId(shoppingListId, productId))
                .thenReturn(Optional.of(productSelection));
        when(productSelectionRepository.save(productSelection)).thenReturn(productSelection);

        checkOrUncheckProductInListService.checkProductInList(shoppingListId, productId);

        InOrder inOrder = Mockito.inOrder(validateShoppingListAndProduct, productSelectionRepository, productSelection);
        inOrder.verify(validateShoppingListAndProduct).validate(shoppingListId, productId);
        inOrder.verify(productSelectionRepository).findByShoppingListIdAndProductId(shoppingListId, productId);
        inOrder.verify(productSelection).setChecked(true);
        inOrder.verify(productSelectionRepository).save(productSelection);
    }

    @Test
    void shouldToggleCheckedAsFalse() {
        when(productSelectionRepository.findByShoppingListIdAndProductId(shoppingListId, productId))
                .thenReturn(Optional.of(productSelection));
        when(productSelectionRepository.save(productSelection)).thenReturn(productSelection);

        checkOrUncheckProductInListService.uncheckProductInList(shoppingListId, productId);

        InOrder inOrder = Mockito.inOrder(validateShoppingListAndProduct, productSelectionRepository, productSelection);
        inOrder.verify(validateShoppingListAndProduct).validate(shoppingListId, productId);
        inOrder.verify(productSelectionRepository).findByShoppingListIdAndProductId(shoppingListId, productId);
        inOrder.verify(productSelection).setChecked(false);
        inOrder.verify(productSelectionRepository).save(productSelection);
    }

    @Test
    void shouldToggleCheckedAsTrueAndCreateProductSelectionIfDoesNotExist() {
        when(productSelectionRepository.findByShoppingListIdAndProductId(shoppingListId, productId))
                .thenReturn(Optional.empty());
        when(productSelectionRepository.save(any(ProductSelection.class))).thenReturn(productSelection);

        checkOrUncheckProductInListService.uncheckProductInList(shoppingListId, productId);

        InOrder inOrder = Mockito.inOrder(validateShoppingListAndProduct, productSelectionRepository, productSelection);
        inOrder.verify(validateShoppingListAndProduct).validate(shoppingListId, productId);
        inOrder.verify(productSelectionRepository).findByShoppingListIdAndProductId(shoppingListId, productId);
        inOrder.verify(productSelection, never()).setChecked(false);
        inOrder.verify(productSelectionRepository).save(any(ProductSelection.class));
    }
}