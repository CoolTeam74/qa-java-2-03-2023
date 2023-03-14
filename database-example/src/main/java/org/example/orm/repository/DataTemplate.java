package org.example.orm.repository;

import java.sql.Connection;
import java.util.Optional;

public interface DataTemplate<T> {
    long insert(Connection connection, T object);

    void update(Connection connection, T object);

    Optional<T> findById(Connection connection, long id);
}
