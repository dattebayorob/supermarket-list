package io.github.dattebayorob.supermarketlist.port.out;

import java.util.UUID;

public interface UncheckProductInListService {
    void uncheckProductInList(UUID shoppingListId, UUID productId);
}
