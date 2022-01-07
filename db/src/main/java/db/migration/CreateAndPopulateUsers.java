package db.migration;

import db.migration.abstraction.ConditionalJavaMigration;

import java.sql.SQLException;
import java.sql.Statement;

import static db.migration.DBMigration.UserRobotId;

public class CreateAndPopulateUsers extends ConditionalJavaMigration {
    @Override
    protected void migrate(Statement statement) throws SQLException {
        execute(
            statement,
            "create table userdetails(",
            getIdColumnLine(),
            "email varchar not null,",
            "password varchar(60) not null,",
            "name varchar(100) not null,",
            "enabled boolean not null default false,",
            "constraint user_id_pkey primary key (id)",
            ");"
        );
        execute(
            statement,
            "create table user_password_history(",
            "user_id UUID not null,",
            "password varchar(60) not null,",
            "constraint user_password_history_pkey primary key (user_id, password),",
            "constraint user_password_history_user_id_fkey foreign key (user_id) references userdetails(id)",
            ");"
        );
        execute(
            statement,
            "insert into userdetails",
                "\t(id, email, password, name, enabled)",
                "values",
                "\t('e3cb2115-98ff-4448-afab-0ba905524a64', 'admin', '$2a$12$bCQf2stUyx8.LdxSDGumOeptQKXHKzT43a7X5qG9SX7C4FZ53w7bK', 'Administrator', true),",
                "\t('"+ UserRobotId+"', 'robot', '$2a$12$Mme0riq8eUSuU4SIDqTNZ.edzGo2w/naXa6ivfMcZPk3g0TNc/BKa', 'Robot', false);"
        );
        execute(statement,
                "insert into user_password_history",
                "\t(user_id, password)",
                "values",
                "\t('"+UserRobotId+"', '$2a$12$Mme0riq8eUSuU4SIDqTNZ.edzGo2w/naXa6ivfMcZPk3g0TNc/BKa');"
        );
    }
}
