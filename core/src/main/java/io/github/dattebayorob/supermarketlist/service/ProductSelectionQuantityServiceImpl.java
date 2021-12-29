package io.github.dattebayorob.supermarketlist.service;

import io.github.dattebayorob.supermarketlist.port.in.ProductSelectionRepository;
import io.github.dattebayorob.supermarketlist.port.out.DecreaseProductQuantityInProductListService;
import io.github.dattebayorob.supermarketlist.port.out.GetProductQuantityInProductListService;
import io.github.dattebayorob.supermarketlist.port.out.IncreaseProductQuantityInProductListService;
import io.github.dattebayorob.supermarketlist.port.out.UpdateProductQuantityInProductListService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductSelectionQuantityServiceImpl implements
                                                    UpdateProductQuantityInProductListService,
                                                    IncreaseProductQuantityInProductListService,
                                                    DecreaseProductQuantityInProductListService,
                                                    GetProductQuantityInProductListService {
    private final ProductSelectionRepository productSelectionRepository;

    @Override
    public void decreaseProductQuantityInProductList(UUID productId, UUID shoppingListId) {
        getProductQuantityInProductList(productId, shoppingListId)
                .filter(quantity -> quantity > 0)
                .ifPresent(
                    quantity -> updateProductQuantityInProductListService(productId, shoppingListId, quantity -1)
                );
    }

    @Override
    public void increaseProductQuantityInProductList(UUID productId, UUID shoppingListId) {
        int quantity = getProductQuantityInProductList(productId, shoppingListId)
                .map(productQuantity -> productQuantity + 1)
                .orElse(1);
        updateProductQuantityInProductListService(productId, shoppingListId, quantity);
    }

    @Override
    public void updateProductQuantityInProductListService(UUID productId, UUID shoppingListId, Integer quantity) {
        productSelectionRepository.updateProductSelectionQuantity(productId, shoppingListId, Optional.ofNullable(quantity).orElse(1));
    }

    @Override
    public Optional<Integer> getProductQuantityInProductList(UUID productId, UUID shoppingListId) {
        return productSelectionRepository.getProductSelectionQuantity(productId, shoppingListId);
    }
}
