package io.github.dattebayorob.supermarketlist.config.db.migration.database;

import io.github.dattebayorob.supermarketlist.config.db.migration.CreateShoppingListStructury;

public class V3__CreateShoppingListStructury extends CreateShoppingListStructury {
    @Override
    protected boolean isInMemory() {
        return false;
    }
}
