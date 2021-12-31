package db.migration.inmemory;

import db.migration.CreateShoppingListStructury;

public class V3__CreateShoppingListStructury extends CreateShoppingListStructury {
    @Override
    protected boolean isInMemory() {
        return true;
    }
}
