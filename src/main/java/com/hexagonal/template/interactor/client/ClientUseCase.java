package com.hexagonal.template.interactor.client;

import com.hexagonal.template.entity.client.Client;
import com.hexagonal.template.interactor.InteractorException;
import com.hexagonal.template.repository.client.ClientCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClientUseCase {

    @Autowired
    ClientCrudRepository clientCrudRepository;

    public List<Client> findClients() {

        return clientCrudRepository.findClients();

    }

    public Client saveClient(Client client) {
        if(!client.isValid())
            throw new InteractorException("Invalid CPF");

        return clientCrudRepository.saveClient(client);
    }

    public Client updateClient(String cpf, Client client) {
        if(!client.isValid())
            throw new InteractorException("Invalid CPF");

        Optional<Client> clientOptional = Optional.ofNullable(findClientByCpf(cpf));

        return clientOptional
                .map(clientFound -> {
                    client.setId(clientFound.getId());
                    return clientCrudRepository.updateClient(cpf, client);
                })
                .orElseThrow(() -> new InteractorException("Client with informed CPF was not found"));
    }

    public void deleteClient(String cpf) {
        Optional.ofNullable(findClientByCpf(cpf))
                .ifPresent(client -> clientCrudRepository.deleteClient(client));
    }

    public Client findClientByCpf(String cpf) {
        return clientCrudRepository.findClientByCpf(cpf);
    }

}
