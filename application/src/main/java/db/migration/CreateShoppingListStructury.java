package db.migration;

import db.migration.abstraction.ConditionalJavaMigration;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateShoppingListStructury extends ConditionalJavaMigration {
    @Override
    protected void migrate(Statement statement) throws SQLException {
        execute(statement,
            "create table shoppinglist(",
                getIdColumnLine(),
                "\tlocked boolean default false,",
                "\tcreatedAt timestamp not null default now(),",
                "\tconstraint shoppinglist_id_pkey primary key(id)",
                ");"
        );
    }
}
