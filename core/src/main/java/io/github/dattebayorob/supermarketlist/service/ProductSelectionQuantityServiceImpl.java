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
    public void decreaseProductQuantityInProductList(UUID productSelectionId) {
        getProductQuantityInProductList(productSelectionId)
                .filter(quantity -> quantity > 0)
                .ifPresent(
                    quantity -> updateProductQuantityInProductListService(productSelectionId, quantity -1)
                );
    }

    @Override
    public void increaseProductQuantityInProductList(UUID productSelectionId) {
        int quantity = getProductQuantityInProductList(productSelectionId)
                .map(productQuantity -> productQuantity + 1)
                .orElse(1);
        updateProductQuantityInProductListService(productSelectionId, quantity);
    }

    @Override
    public void updateProductQuantityInProductListService(UUID productSelectionId, Integer quantity) {
        productSelectionRepository.updateProductSelectionQuantity(productSelectionId, quantity);
    }

    @Override
    public Optional<Integer> getProductQuantityInProductList(UUID productSelectionId) {
        return productSelectionRepository.getProductSelectionQuantity(productSelectionId);
    }
}
