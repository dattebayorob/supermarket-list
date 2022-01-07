package db.migration.database;

import db.migration.CreateAndPopulateUsers;

public class V7__CreateAndPopulateUsers extends CreateAndPopulateUsers {
    @Override
    protected boolean isInMemory() {
        return false;
    }
}