package io.github.dattebayorob.supermarketlist.config.db.migration.inmemory;

import io.github.dattebayorob.supermarketlist.config.db.migration.CreateShoppingListStructury;

public class V3__CreateShoppingListStructury extends CreateShoppingListStructury {
    @Override
    protected boolean isInMemory() {
        return true;
    }
}
