package com.hibernate.naming;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PrefixNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    private static final String PREFIX = "wp_";

    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment context) {
        // Name with custom prefix
        String tableName = PREFIX + logicalName.getText().toLowerCase();
        return Identifier.toIdentifier(tableName);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier logicalName, JdbcEnvironment context) {
        // Name with custom prefix
        String columnName = PREFIX + logicalName.getText().toLowerCase();
        return Identifier.toIdentifier(columnName);
    }
}
