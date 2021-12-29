package io.github.dattebayorob.supermarketlist.port.in;

import io.github.dattebayorob.supermarketlist.domain.ProductSelection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductSelectionRepository {
    ProductSelection save(ProductSelection productSelection);
    void removeByProductIdAndShoppingListId(UUID productId, UUID shoppingListId);
    void updateProductSelectionQuantity(UUID productId, UUID shoppingListId, int quantity);
    Optional<Integer> getProductSelectionQuantity(UUID productId, UUID shoppingListId);
    List<ProductSelection> findByShoppingListId(UUID shoppingListId);
}
