package org.example.orm.service;

import org.example.orm.model.Client;
import org.example.orm.repository.DataTemplate;

import java.util.Optional;

public class DBServiceClientImpl implements DBServiceClient {
    private final DataTemplate<Client> dataTemplate;

    public DBServiceClientImpl(DataTemplate<Client> dataTemplate) {
        this.dataTemplate = dataTemplate;
    }

    @Override
    public Client saveClient(Client client) {
        if (client.getId() == null) {
           long clientId =  dataTemplate.insert(client);
           Client createdClient = new Client(clientId, client.getName());
           return createdClient;
        }
        dataTemplate.update(client);
        return client;
    }

    @Override
    public Optional<Client> getClient(long id) {
        return dataTemplate.findById(id);
    }
}
