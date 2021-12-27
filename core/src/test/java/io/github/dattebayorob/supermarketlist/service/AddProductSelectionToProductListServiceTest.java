package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.domain.Product;
import io.github.dattebayorob.supermarketlist.domain.ProductSelection;
import io.github.dattebayorob.supermarketlist.domain.ShoppingList;
import io.github.dattebayorob.supermarketlist.domain.User;
import io.github.dattebayorob.supermarketlist.exception.BusinessException;
import io.github.dattebayorob.supermarketlist.exception.ProductNotFoundException;
import io.github.dattebayorob.supermarketlist.port.in.ProductRepository;
import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.port.in.ShoppingListRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddProductSelectionToProductListServiceTest {
    @Mock ProductRepository productRepository;
    @Mock ShoppingListRepository shoppingListRepository;
    @Mock ProductSelectionRepository productSelectionRepository;
    AddProductSelectionToProductListServiceImpl addProductSelectionToProductListService;
    @BeforeEach
    void beforeEach() {
        addProductSelectionToProductListService = new AddProductSelectionToProductListServiceImpl(
                productRepository, shoppingListRepository, productSelectionRepository
        );
    }

    @Test
    void shouldThrowsBusinessExceptionIfShoppingListIsLocked() {
        var productSelection = getProductSelection();
        var shoppingList = productSelection.getShoppingList();
        shoppingList.setLocked(true);

        when(shoppingListRepository.findById(shoppingList.getId())).thenReturn(Optional.of(shoppingList));
        assertThatThrownBy(() -> addProductSelectionToProductListService.addProductSelectionToShoppingList(shoppingList.getId(), productSelection))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Shopping list is locked");
    }

    @Test
    void shouldThrowsProductNotFoundExceptionIfProductsDoesntExist() {
        var productSelection = getProductSelection();
        var shoppingList = productSelection.getShoppingList();
        UUID productId = productSelection.getProduct().getId();

        when(shoppingListRepository.findById(shoppingList.getId())).thenReturn(Optional.of(shoppingList));
        when(productRepository.existsById(productId)).thenReturn(false);

        assertThatThrownBy(() -> addProductSelectionToProductListService.addProductSelectionToShoppingList(shoppingList.getId(), productSelection))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("No product found for id "+productId);
    }

    @Test
    void shouldSetDefaultQuantityIfInvalidQuantityIsSent() {
        var productSelection = getProductSelection();
        productSelection.setQuantity(-4);
        var shoppingList = productSelection.getShoppingList();
        UUID productId = productSelection.getProduct().getId();

        when(shoppingListRepository.findById(shoppingList.getId())).thenReturn(Optional.of(shoppingList));
        when(productRepository.existsById(productId)).thenReturn(true);

        addProductSelectionToProductListService.addProductSelectionToShoppingList(productSelection.getShoppingList().getId(), productSelection);

        Assertions.assertEquals(1, productSelection.getQuantity());

        verify(productSelectionRepository).save(productSelection);
    }

    ProductSelection getProductSelection() {
        var productSelection = new ProductSelection();
        productSelection.setShoppingList(getShoppingList());
        productSelection.setProduct(getProduct());
        productSelection.setQuantity(2);
        productSelection.setUser(getUser());
        return productSelection;
    }

    User getUser() {
        return new User();
    }

    Product getProduct() {
        var product = new Product();
        product.setId(UUID.randomUUID());
        return product;
    }

    ShoppingList getShoppingList() {
        var shoppingList = new ShoppingList();
        shoppingList.setEmpty(false);
        shoppingList.setCreatedAt(LocalDateTime.now());
        shoppingList.setId(UUID.randomUUID());
        return shoppingList;
    }
}