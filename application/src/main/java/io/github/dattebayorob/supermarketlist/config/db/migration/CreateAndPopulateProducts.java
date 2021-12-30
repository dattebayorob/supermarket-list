package io.github.dattebayorob.supermarketlist.config.db.migration;

import io.github.dattebayorob.supermarketlist.config.db.migration.abstraction.ConditionalJavaMigration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public abstract class CreateAndPopulateProducts extends ConditionalJavaMigration {
    private static final String CATEGORY_TEMPEROS = "7a4b5ca8-4beb-41ec-941f-2e44abafb05d";
    private static final String CATEGORY_CAFE_MANHA = "f5dbc5e7-4ab6-40f6-93ad-209116fa49a1";

    @Override
    protected void migrate(Statement statement) throws SQLException {
        createTableProduct(statement);
        populateProducts(statement);
    }

    private void createTableProduct(Statement select) throws SQLException {
        var lines = new StringBuilder("create table product(\n");
        lines.append(getIdColumnLine());
        lines.append("\tname varchar not null,\n");
        lines.append("\tcategory_id UUID not null,\n");
        lines.append("\tconstraint product_pkey primary key (id),");
        lines.append("\tconstraint product_category_fkey foreign key (category_id) references product_category(id)\n");
        lines.append(");");
        select.execute(lines.toString());
    }

    private void populateProducts(Statement select) throws SQLException {
        var lines = new StringBuilder("insert into\n\tproduct(id, name, category_id)\nvalues\n");
        for ( String product : new String[] { "Pão de Forma", "Pão bola", "Café"} ) {
            lines.append(String.format("\t('%s', '%s', '%s'),%n", UUID.randomUUID(), product, CATEGORY_CAFE_MANHA));
        }
        lines.append(String.format("\t('%s','%s','%s');", UUID.randomUUID(), "Cominho", CATEGORY_TEMPEROS));
        select.execute(lines.toString());
    }
}
