package io.github.dattebayorob.supermarketlist.config.db.migration.database;

import io.github.dattebayorob.supermarketlist.config.db.migration.CreateAndPopulateProductCategories;

public class V1__CreateAndPopulateProductCategories extends CreateAndPopulateProductCategories {
    @Override
    protected boolean isInMemory() {
        return false;
    }
}
