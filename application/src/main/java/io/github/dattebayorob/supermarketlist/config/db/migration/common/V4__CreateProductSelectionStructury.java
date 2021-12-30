package io.github.dattebayorob.supermarketlist.config.db.migration.common;

import io.github.dattebayorob.supermarketlist.config.db.migration.abstraction.JavaMigration;

import java.sql.SQLException;
import java.sql.Statement;

public class V4__CreateProductSelectionStructury extends JavaMigration {
    @Override
    protected void migrate(Statement statement) throws SQLException {
        execute(
            statement,
            "create table product_selection(",
            "\tproduct_id UUID not null,",
            "\tshoppinglist_id UUID not null,",
            "\tquantity int not null default 0,",
            "\tconstraint product_id_shoppinglist_id_pkey primary key (product_id, shoppinglist_id),",
            "\tconstraint product_id_fkey foreign key (product_id) references product (id),",
            "\tconstraint shoppinglist_id_fkey foreign key (shoppinglist_id) references shoppinglist (id)",
            ");"
        );
    }
}
