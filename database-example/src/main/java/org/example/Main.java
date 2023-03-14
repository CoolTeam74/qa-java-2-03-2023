package org.example;

import org.example.orm.DriverManagerDataSource;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class Main {
    public static final String DATABASE_URL = "jdbc:postgresql://localhost/test_db_2";

    public static final String USERNAME = "postgres";

    public static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        DataSource dataSource = new DriverManagerDataSource(DATABASE_URL, USERNAME, PASSWORD);
        flywayMigrate(dataSource);
    }

    private static void flywayMigrate(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
    }
}
