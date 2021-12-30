package io.github.dattebayorob.supermarketlist.config.db.migration.abstraction;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class JavaMigration extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        try(Statement statement = context.getConnection().createStatement()) {
            migrate(statement);
        }
    }

    protected abstract void migrate(Statement statement) throws SQLException;

    protected void execute(Statement statement, String ... statements) throws SQLException {
        statement.execute(Stream.of(statements).collect(Collectors.joining(" \n")));
    }
}
