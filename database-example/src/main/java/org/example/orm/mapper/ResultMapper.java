package org.example.orm.mapper;

import org.example.orm.ReflectionUtility;
import org.example.orm.exception.ResultMapperException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class ResultMapper<T> implements Function<ResultSet, T> {
    private final EntityClassMetaData<T> metaData;

    public ResultMapper(EntityClassMetaData<T> metaData) {
        this.metaData = metaData;
    }

    @Override
    public T apply(ResultSet resultSet) {
        try {
            T object = metaData.getConstructor().newInstance();
            if (resultSet.next()) {
                for (Field f : metaData.getAllFields()) {
                    ReflectionUtility.setValue(f, object, resultSet.getObject(f.getName()));
                }
            }
            return object;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | SQLException e) {
            throw new ResultMapperException(e.getMessage());
        }
    }
}
