package org.example.orm.mapper;

import org.example.orm.exception.EntityMetaDataException;
import org.example.orm.repository.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T>{
    private final Class<T> aClass;

    private final Field idFiled;

    private final List<Field> withoutIdFields;

    private final Constructor<T> constructor;

    public EntityClassMetaDataImpl(Class<T> aClass) {
        this.aClass = aClass;
        this.idFiled = findIdField();
        this.withoutIdFields = findWithoutIdFields();
        this.constructor = findConstructorWithoutParameters();
    }

    private Constructor<T> findConstructorWithoutParameters() {
        try {
            return aClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new EntityMetaDataException(e.getMessage());
        }
    }

    private List<Field> findWithoutIdFields() {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(Id.class))
                .collect(Collectors.toList());
    }

    private Field findIdField() {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst().get();
    }

    @Override
    public String getName() {
        return aClass.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return idFiled;
    }

    @Override
    public List<Field> getAllFields() {
        List<Field> fields = new ArrayList<>(withoutIdFields);
        fields.add(idFiled);
        return fields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return withoutIdFields;
    }
}
