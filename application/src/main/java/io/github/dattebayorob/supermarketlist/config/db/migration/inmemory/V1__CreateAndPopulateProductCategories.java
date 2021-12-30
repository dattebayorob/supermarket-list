package io.github.dattebayorob.supermarketlist.config.db.migration.inmemory;

import io.github.dattebayorob.supermarketlist.config.db.migration.CreateAndPopulateProductCategories;

public class V1__CreateAndPopulateProductCategories extends CreateAndPopulateProductCategories {
    @Override
    protected boolean isInMemory() {
        return true;
    }
}
