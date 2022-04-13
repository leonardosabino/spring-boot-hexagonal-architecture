package com.hexagonal.template.dataSource.database.client;

import com.hexagonal.template.entity.client.Client;
import com.hexagonal.template.repository.client.ClientCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(prefix = "datasource.type", value = "database", havingValue="true")
public class ClientDatasourceDb implements ClientCrudRepository {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client saveClient(Client client) {
        ClientEntity clientEntity = clientRepository
                .save(MapperClient.INSTANCE.clientToClientEntity(client));

        return MapperClient.INSTANCE.clientEntityToClient(clientEntity);
    }

    @Override
    public Client updateClient(String cpf, Client client) {
        var clientEntity = MapperClient.INSTANCE.clientToClientEntity(client);
        var saved = clientRepository.save(clientEntity);

        return MapperClient.INSTANCE.clientEntityToClient(saved);
    }

    @Override
    public Client findClientByCpf(String cpf) {
        return clientRepository.findByCpf(cpf)
                .map(MapperClient.INSTANCE::clientEntityToClient)
                .orElse(null);
    }

    @Override
    public void deleteClient(Client client) {
        clientRepository.findByCpf(client.getCpf())
                .ifPresent(clientEntity -> clientRepository.delete(clientEntity));
    }

    @Override
    public List<Client> findClients() {
        return clientRepository.findAll()
                .stream()
                .map(MapperClient.INSTANCE::clientEntityToClient)
                .collect(Collectors.toList());
    }
}
