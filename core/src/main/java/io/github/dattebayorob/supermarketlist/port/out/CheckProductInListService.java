package io.github.dattebayorob.supermarketlist.port.out;

import java.util.UUID;

public interface CheckProductInListService {
    void checkProductInList(UUID shoppingListId, UUID productId);
}
