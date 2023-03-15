package org.example;

import org.example.orm.DriverManagerDataSource;
import org.example.orm.mapper.EntityClassMetaData;
import org.example.orm.mapper.EntityClassMetaDataImpl;
import org.example.orm.mapper.EntitySQLMetaData;
import org.example.orm.mapper.EntitySQLMetaDataImpl;
import org.example.orm.model.Client;
import org.example.orm.repository.DataTemplate;
import org.example.orm.repository.DataTemplateJdbc;
import org.example.orm.repository.DbExecutor;
import org.example.orm.repository.DbExecutorImpl;
import org.example.orm.service.DBServiceClient;
import org.example.orm.service.DBServiceClientImpl;
import org.example.orm.sesseion.TransactionRunner;
import org.example.orm.sesseion.TransactionRunnerJdbc;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class Main {
    public static final String DATABASE_URL = "jdbc:postgresql://localhost/test_db_2";

    public static final String USERNAME = "postgres";

    public static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        DataSource dataSource = new DriverManagerDataSource(DATABASE_URL, USERNAME, PASSWORD);
        flywayMigrate(dataSource);

        TransactionRunner transactionRunner = new TransactionRunnerJdbc(dataSource);
        DbExecutor dbExecutor = new DbExecutorImpl();
        EntityClassMetaData<Client> metaData = new EntityClassMetaDataImpl<>(Client.class);
        EntitySQLMetaData sqlMetaData = new EntitySQLMetaDataImpl<>(metaData);
        // important!
        DataTemplate<Client> clientDataTemplate = new DataTemplateJdbc<>(dbExecutor, sqlMetaData, metaData);

        DBServiceClient clientService = new DBServiceClientImpl(clientDataTemplate, transactionRunner);
        Client vasya = clientService.saveClient(Client.builder().name("Vasya").build());
        // ...

    }

    private static void flywayMigrate(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
    }
}
