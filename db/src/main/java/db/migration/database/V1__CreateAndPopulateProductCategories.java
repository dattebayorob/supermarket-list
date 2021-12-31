package db.migration.database;

import db.migration.CreateAndPopulateProductCategories;

public class V1__CreateAndPopulateProductCategories extends CreateAndPopulateProductCategories {
    @Override
    protected boolean isInMemory() {
        return false;
    }
}
