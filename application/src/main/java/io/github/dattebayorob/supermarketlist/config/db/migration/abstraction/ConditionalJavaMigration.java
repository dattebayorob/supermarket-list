package io.github.dattebayorob.supermarketlist.config.db.migration.abstraction;

public abstract class ConditionalJavaMigration extends JavaMigration{
    protected String getIdColumnLine() {
        return "\tid UUID default " + (isInMemory() ? "random_uuid()" : "uuid_generate_v4()") + " not null,";
    }
    protected boolean isInMemory() {
        return false;
    }
}
