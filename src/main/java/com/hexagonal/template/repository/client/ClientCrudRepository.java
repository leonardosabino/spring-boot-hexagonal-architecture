package com.hexagonal.template.repository.client;

import com.hexagonal.template.entity.client.Client;

import java.util.List;


public interface ClientCrudRepository {

    Client saveClient(Client client);
    Client updateClient(String cpf, Client client);
    Client findClientByCpf(String cpf);
    void deleteClient(Client client);
    List<Client> findClients();

}
