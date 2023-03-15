package org.example.orm.service;

import org.example.orm.model.Client;
import org.example.orm.repository.DataTemplate;
import org.example.orm.sesseion.TransactionRunner;

import java.util.Optional;

public class DBServiceClientImpl implements DBServiceClient {
    private final DataTemplate<Client> dataTemplate;
    private final TransactionRunner transactionRunner;

    public DBServiceClientImpl(DataTemplate<Client> dataTemplate, TransactionRunner transactionRunner) {
        this.dataTemplate = dataTemplate;
        this.transactionRunner = transactionRunner;
    }

    @Override
    public Client saveClient(Client client) {
        return transactionRunner.doInTransaction(connection -> {
            if (client.getId() == null) {
                long clientId = dataTemplate.insert(connection, client);
                Client createdClient = new Client(clientId, client.getName());
                return createdClient;
            }
            dataTemplate.update(connection, client);
            return client;
        });
    }

    @Override
    public Optional<Client> getClient(long id) {
        return transactionRunner.doInTransaction(connection -> {
            return dataTemplate.findById(connection, id);
        });
    }
}
