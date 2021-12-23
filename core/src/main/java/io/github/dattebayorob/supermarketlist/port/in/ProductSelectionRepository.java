package io.github.dattebayorob.supermarketlist.port.in;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;

import java.util.Optional;
import java.util.UUID;

public interface ProductSelectionRepository {
    ProductSelection save(ProductSelection productSelection);
    void updateProductSelectionQuantity(UUID productSelectionId, int quantity);
    Optional<Integer> getProductSelectionQuantity(UUID productSelectionQuantity);
}
