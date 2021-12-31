package db.migration.common;

import db.migration.abstraction.JavaMigration;

import java.sql.SQLException;
import java.sql.Statement;

public class V6__CreateColumnProductSelectionChecked extends JavaMigration {
    @Override
    protected void migrate(Statement statement) throws SQLException {
        execute(
            statement, "alter table product_selection add column checked boolean not null default false"
        );
    }
}
