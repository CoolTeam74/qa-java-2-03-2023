package org.example.orm.repository;

import org.example.orm.exception.DataBaseOperationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DbExecutorImpl implements DbExecutor{
    @Override
    public long executeStatement(Connection connection, String sql, List<Object> params) {
        try(PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for(int idx = 0; idx < params.size(); idx++) {
                pst.setObject(idx+1, params.get(idx));
            }
            pst.executeUpdate();
            try (ResultSet rs = pst.getResultSet()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DataBaseOperationException(e.getMessage());
        }
    }

    @Override
    public <T> Optional<T> executeSelect(Connection connection, String url, List<Object> params, Function<ResultSet, T> mapper) {
        return Optional.empty();
    }
}
