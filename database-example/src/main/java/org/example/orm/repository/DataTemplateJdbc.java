package org.example.orm.repository;

import org.example.orm.ReflectionUtility;
import org.example.orm.mapper.EntityClassMetaData;
import org.example.orm.mapper.EntitySQLMetaData;
import org.example.orm.mapper.ResultMapper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DataTemplateJdbc<T> implements DataTemplate<T> {
    private final DbExecutor dbExecutor;
    private final ResultMapper<T> resultMapper;
    private final EntitySQLMetaData entitySqlMetaData;

    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor,
                            EntitySQLMetaData entitySqlMetaData,
                            EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.resultMapper = new ResultMapper<>(entityClassMetaData);
        this.entitySqlMetaData = entitySqlMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }


    @Override
    public long insert(Connection connection, T object) {
        return dbExecutor.executeStatement(connection, entitySqlMetaData.getInsertSql(), createArgumentList(object, entityClassMetaData.getAllFields()));
    }

    @Override
    public void update(Connection connection, T object) {
        dbExecutor.executeStatement(connection, entitySqlMetaData.getUpdateSql(), createArgumentList(object, entityClassMetaData.getAllFields()));
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
       return dbExecutor.executeSelect(connection, entitySqlMetaData.getSelectByIdSql(), List.of(id), resultMapper);
    }

    private List<Object> createArgumentList(T model, List<Field> fields) {
        return fields.stream()
                .map(f -> ReflectionUtility.getValueFromObjectByField(f, model))
                .toList();
    }
}
