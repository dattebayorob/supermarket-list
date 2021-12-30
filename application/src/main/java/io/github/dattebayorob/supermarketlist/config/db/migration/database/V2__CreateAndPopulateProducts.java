package io.github.dattebayorob.supermarketlist.config.db.migration.database;

import io.github.dattebayorob.supermarketlist.config.db.migration.CreateAndPopulateProducts;

public class V2__CreateAndPopulateProducts extends CreateAndPopulateProducts {
    @Override
    protected boolean isInMemory() {
        return false;
    }
}
