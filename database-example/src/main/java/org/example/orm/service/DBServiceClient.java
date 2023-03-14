package org.example.orm.service;

import org.example.orm.model.Client;

import java.util.Optional;

public interface DBServiceClient {
    Client saveClient(Client client);

    Optional<Client> getClient(long id);
}
