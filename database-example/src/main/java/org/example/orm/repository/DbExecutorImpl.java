package org.example.orm.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DbExecutorImpl implements DbExecutor{
    @Override
    public long executeStatement(Connection connection, String sql, List<Object> params) {
        return 0;
    }

    @Override
    public <T> Optional<T> executeSelect(Connection connection, String url, List<Object> params, Function<ResultSet, T> mapper) {
        return Optional.empty();
    }
}
