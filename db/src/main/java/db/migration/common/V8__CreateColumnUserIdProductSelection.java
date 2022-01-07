package db.migration.common;

import db.migration.abstraction.JavaMigration;

import java.sql.SQLException;
import java.sql.Statement;

import static db.migration.DBMigration.UserRobotId;

public class V8__CreateColumnUserIdProductSelection extends JavaMigration {
    @Override
    protected void migrate(Statement statement) throws SQLException {
        execute(
            statement,
            "alter table product_selection add column user_id UUID;",
            "alter table product_selection add constraint product_selection_user_id_fkey foreign key (user_id) references userdetails(id);",
            "update product_selection set user_id = '"+ UserRobotId +"';",
            "alter table product_selection alter column user_id set not null;"
        );
    }
}
