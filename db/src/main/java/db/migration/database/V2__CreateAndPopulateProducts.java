package db.migration.database;

import db.migration.CreateAndPopulateProducts;

public class V2__CreateAndPopulateProducts extends CreateAndPopulateProducts {
    @Override
    protected boolean isInMemory() {
        return false;
    }
}
