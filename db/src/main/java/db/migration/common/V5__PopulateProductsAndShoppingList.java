package db.migration.common;

import db.migration.abstraction.JavaMigration;

import java.sql.SQLException;
import java.sql.Statement;

public class V5__PopulateProductsAndShoppingList extends JavaMigration {
    @Override
    protected void migrate(Statement statement) throws SQLException {
        execute(
            statement,
            "insert into shoppinglist",
            "\t(locked, createdAt)",
            "values",
            "\t(true, '2021-1-1 00:00:00'),",
            "\t(false, '2021-2-1 00:00:00');"
        );
        execute(
            statement,
            "insert into product",
            "\t(name, category_id)",
            "values",
            "\t('Laranja', '69882a7f-21ae-4872-8818-49d45bd8d81a'),",
            "\t('Limão', '69882a7f-21ae-4872-8818-49d45bd8d81a'),",
            "\t('Tangerina', '69882a7f-21ae-4872-8818-49d45bd8d81a'),",
            "\t('Batata', '69882a7f-21ae-4872-8818-49d45bd8d81a'),",
            "\t('Beterraba', '69882a7f-21ae-4872-8818-49d45bd8d81a');"
        );
    }
}
