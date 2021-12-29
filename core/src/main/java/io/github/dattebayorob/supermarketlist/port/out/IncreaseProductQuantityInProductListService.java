package io.github.dattebayorob.supermarketlist.port.out;

import java.util.UUID;

public interface IncreaseProductQuantityInProductListService {
    void increaseProductQuantityInProductList(UUID productId, UUID shoppingListId);
}
