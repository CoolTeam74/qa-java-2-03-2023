package org.example.orm.mapper;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData{
    private final String selectFindByIdSql;
    private final String selectFindAllSql;
    private final String insertSql;
    private final String updateSql;

    private final EntityClassMetaData<T> metadata;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> metadata) {
        this.metadata = metadata;

        selectFindAllSql = String.format("SELECT * FROM %s", metadata.getName());
        selectFindByIdSql = String.format("SELECT * FROM %s WHERE %s = ?", metadata.getName(), metadata.getIdField().getName());
        updateSql = String.format("UPDATE %s SET %s  WHERE %s = ?", metadata.getName(), creaateUpdateSetString(), metadata.getIdField().getName());
        insertSql = String.format("INSERT INTO %s (%s) VALUES (%s)", metadata.getName(), getFieldsStringWithoutId(), createValuesList());
    }

    @Override
    public String getSelectAllSql() {
        return selectFindAllSql;
    }

    @Override
    public String getSelectByIdSql() {
        return selectFindByIdSql;
    }

    @Override
    public String getInsertSql() {
        return insertSql;
    }

    @Override
    public String getUpdateSql() {
        return updateSql;
    }

    private String getFieldsStringWithoutId() {
        return metadata.getFieldsWithoutId().stream()
                .map(Field::getName)
                .collect(Collectors.joining(","));
    }

    private String creaateUpdateSetString() {
        return metadata.getFieldsWithoutId().stream()
                .map(f -> f.getName() + " = ?")
                .collect(Collectors.joining(","));
    }

    private String createValuesList() {
        return metadata.getFieldsWithoutId().stream()
                .map(f -> "?")
                .collect(Collectors.joining(","));
    }
}
