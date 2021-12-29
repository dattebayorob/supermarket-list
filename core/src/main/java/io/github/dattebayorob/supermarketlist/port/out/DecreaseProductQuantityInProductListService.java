package io.github.dattebayorob.supermarketlist.port.out;

import java.util.UUID;

public interface DecreaseProductQuantityInProductListService {
    void decreaseProductQuantityInProductList(UUID productId, UUID shoppingListId);
}
