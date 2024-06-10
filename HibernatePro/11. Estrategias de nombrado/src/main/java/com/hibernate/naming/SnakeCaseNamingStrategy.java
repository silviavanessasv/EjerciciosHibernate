package com.hibernate.naming;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class SnakeCaseNamingStrategy extends PhysicalNamingStrategyStandardImpl {


    @Override
    public Identifier toPhysicalColumnName(Identifier logicalName, JdbcEnvironment context) {
        return this.toSnakeCase(logicalName);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment context) {
        return this.toSnakeCase(logicalName);
    }

    @Override
    public Identifier toPhysicalCatalogName(Identifier logicalName, JdbcEnvironment context) {
        return this.toSnakeCase(logicalName);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier logicalName, JdbcEnvironment context) {
        return this.toSnakeCase(logicalName);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier logicalName, JdbcEnvironment context) {
        return this.toSnakeCase(logicalName);
    }

    private Identifier toSnakeCase(Identifier identifier) {
        if (identifier == null) {
            return null;
        }
        String name = identifier.getText();
        String snakeCase = name.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
        return Identifier.toIdentifier(snakeCase);
    }
}
