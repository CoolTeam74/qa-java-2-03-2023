package org.example.orm.repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DataTemplateJdbc<T> implements DataTemplate<T> {
    private final DbExecutor dbExecutor;
    private final ResultMapper<T> resultMapper;
    private final EntitySqlMetaData entitySqlMetaData;

    private final EntityClassMetaData entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor) {
        this.dbExecutor = dbExecutor;
    }


    @Override
    public long insert(Connection connection, T object) {
        dbExecutor.executeStatement(connection, entitySqlMetaData.getInsertSql(), createArgumentList(object, entitySqlMetaData.getAllFields()));
    }

    @Override
    public void update(Connection connection, T object) {
        dbExecutor.executeStatement(connection, entitySqlMetaData.getUpdateSql(), createArgumentList(object, entitySqlMetaData.getAllFields()));
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
       dbExecutor.executeSelect(connection, entitySqlMetaData.getSelectByIdSql(), List.of(id), resultMapper);
    }

    private List<Object> createArgumentList(T model, List<Field> fields) {
        fields.stream()
                .map(f -> ReflectionUtils.getValueFromField(f, moodel))
                .toList();
    }
}
