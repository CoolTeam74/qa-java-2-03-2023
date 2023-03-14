package org.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String DATABASE_URL = "jdbc:postgresql://localhost/test_db_2";

    public static final String SELECT_ADMIN_USERS = "SELECT u.name AS employee_name FROM users_to_roles ur\n" +
            "                       JOIN users u ON ur.user_id = u.id\n" +
            "                       JOIN roles r ON ur.role_id = r.id\n" +
            "WHERE r.name = ?";

    public static final String SELECT_ADMIN_USERS_FULL = "SELECT u.id AS user_id, u.name AS employee_name FROM users_to_roles ur\n" +
            "                       JOIN users u ON ur.user_id = u.id\n" +
            "                       JOIN roles r ON ur.role_id = r.id\n" +
            "WHERE r.name = ?";

    public static void main(String[] args) throws SQLException {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "postgres")) {
            try(PreparedStatement statement =  connection.prepareStatement(SELECT_ADMIN_USERS)) {
                statement.setString(1, "ADMIN");
                try(ResultSet resultSet = statement.executeQuery()) {
                    List<String> employeeNames = new ArrayList<>();

                    while(resultSet.next()) {
                        employeeNames.add(resultSet.getString("employee_name"));
                    }
                    System.out.println(employeeNames);
                }
            }

            try(PreparedStatement statement =  connection.prepareStatement(SELECT_ADMIN_USERS_FULL)) {
                statement.setString(1, "ADMIN");
                try(ResultSet resultSet = statement.executeQuery()) {
                    List<User> employees = new ArrayList<>();

                    while(resultSet.next()) {
                        User user = new User();
                        user.setId(resultSet.getLong("user_id"));
                        user.setName(resultSet.getString("employee_name"));

                        try(PreparedStatement stmt =  connection.prepareStatement(SELECT_ADMIN_USERS)) {
                            statement.setLong(1, user.getId());
                            try(ResultSet rs = stmt.executeQuery()) {
                                List<Role> rolesForEmployee = new ArrayList<>();

                                while(resultSet.next()) {
                                    Role role = new Role();
                                    role.setId(rs.getLong("role_id"));
                                    role.setName(rs.getString("role_name"));

                                }
                                user.setRoles(rolesForEmployee);
                            }
                        }

                        employees.add(user);
                    }
                    System.out.println(employees);
                }
            }
        }
    }
}
