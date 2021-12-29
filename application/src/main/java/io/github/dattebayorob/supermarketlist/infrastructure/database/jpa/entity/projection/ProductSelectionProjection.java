package io.github.dattebayorob.supermarketlist.infrastructure.database.jpa.entity.projection;

import java.util.UUID;

public interface ProductSelectionProjection {
    UUID getProductId();
    String getProductName();
    UUID getShoppingListId();
    UUID getProductCategoryId();
    String getProductCategoryName();
    int getQuantity();
}
