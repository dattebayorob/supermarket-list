package io.github.dattebayorob.supermarketlist.config.db.migration;

import io.github.dattebayorob.supermarketlist.config.db.migration.abstraction.ConditionalJavaMigration;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class CreateAndPopulateProductCategories extends ConditionalJavaMigration {
    @Override
    protected void migrate(Statement statement) throws SQLException {
        enableUUIDExtension(statement);
        createTableProductCategory(statement);
        populateProductCategories(statement);
    }
    private void enableUUIDExtension(Statement statement) throws SQLException {
        if ( !isInMemory() ) {
            statement.execute("CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\";");
        }
    }
    private void createTableProductCategory(Statement statement) throws SQLException {
        statement.execute("create table product_category(\n"
            + getIdColumnLine()
            + "\tname varchar not null,\n"
            + "\t constraint product_category_pkey primary key(id)"
            + ");"
        );
    }
    private void populateProductCategories(Statement statement) throws SQLException{
        var inserInto = new StringBuilder("insert into\n\tproduct_category(id, name)\nvalues\n");
        inserInto.append("\t('f5dbc5e7-4ab6-40f6-93ad-209116fa49a1','Café da manhã/Padaria'),\n");
        inserInto.append("\t('f24b833d-672c-45d4-9690-7a7b2d87f281','Mercearia em geral e enlatados'),\n");
        inserInto.append("\t('343ef472-8e6a-493a-bfb1-252bc1802ee7','Bebidas'),\n");
        inserInto.append("\t('7a4b5ca8-4beb-41ec-941f-2e44abafb05d','temperos'),\n");
        inserInto.append("\t('69882a7f-21ae-4872-8818-49d45bd8d81a','Frutas e Legumes'),\n");
        inserInto.append("\t('352db31d-8d11-4f6c-b4ee-b39b7cf63be4','Carnes e frios'),\n");
        inserInto.append("\t('df5c8c85-00a3-42f2-95c6-210bc5b0948a','Pet'),\n");
        inserInto.append("\t('2968f7fa-1b44-4794-b830-3449d1d16ebc','Produtos de Limpeza/Utilidades'),\n");
        inserInto.append("\t('e12f64ba-e507-4dd1-ad33-07060d93285b','Higiene pessoal');");
        statement.execute(inserInto.toString());
    }
}
