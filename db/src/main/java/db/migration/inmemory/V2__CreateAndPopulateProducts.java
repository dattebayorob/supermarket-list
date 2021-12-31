package db.migration.inmemory;

import db.migration.CreateAndPopulateProducts;

public class V2__CreateAndPopulateProducts extends CreateAndPopulateProducts {
    @Override
    protected boolean isInMemory() {
        return true;
    }
}
