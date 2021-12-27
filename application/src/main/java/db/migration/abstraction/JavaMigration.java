package db.migration.abstraction;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class JavaMigration extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        try(Statement statement = context.getConnection().createStatement()) {
            migrate(statement);
        }
    }

    protected abstract void migrate(Statement statement) throws SQLException;

    protected String getIdColumnLine() {
        return "\tid UUID default " + (isInMemory() ? "random_uuid()" : "uuid_generate_v4()") + " not null,\n";
    }
    protected boolean isInMemory() {
        return false;
    };
}
