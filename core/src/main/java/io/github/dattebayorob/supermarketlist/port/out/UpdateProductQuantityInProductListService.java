package io.github.dattebayorob.supermarketlist.port.out;

import java.util.UUID;

public interface UpdateProductQuantityInProductListService {
    void updateProductQuantityInProductListService(UUID productId, UUID shoppingListId, Integer quantity);
}
