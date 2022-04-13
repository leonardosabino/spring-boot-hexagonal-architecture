package com.hexagonal.template.dataSource.restclient.client;

import com.hexagonal.template.entity.client.Client;
import com.hexagonal.template.repository.client.ClientCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(prefix = "datasource.type", value = "restclient", havingValue="true")
public class ClientDatasourceFeign implements ClientCrudRepository {

    @Autowired
    private LocalFeignClient localFeignClient;

    @Override
    public Client saveClient(Client client) {
        return localFeignClient.saveClient(client).getBody();
    }

    @Override
    public Client updateClient(String cpf, Client client) {
        return localFeignClient.updateClient(client).getBody();
    }

    @Override
    public Client findClientByCpf(String cpf) {
        return localFeignClient.FindClientByCpf(cpf).getBody();
    }

    @Override
    public void deleteClient(Client client) {
        localFeignClient.deleteClient(client.getCpf());
    }

    @Override
    public List<Client> findClients() {
        return localFeignClient.findClients().getBody();
    }
}
