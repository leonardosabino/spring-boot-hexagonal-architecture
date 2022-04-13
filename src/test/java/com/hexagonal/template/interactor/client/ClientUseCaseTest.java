package com.hexagonal.template.interactor.client;

import com.hexagonal.template.entity.client.Client;
import com.hexagonal.template.interactor.InteractorException;
import com.hexagonal.template.repository.client.ClientCrudRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ClientUseCaseTest {

    @InjectMocks
    ClientUseCase clientService;

    @Mock
    ClientCrudRepository clientCrudRepository;

    @Test
    void shouldThrowInternalErrorException() {
        final var cpf = "invalidCPF";
        final var rg = "000000002";

        var client = Client.builder()
                .id(1L)
                .name("Random name")
                .age("18")
                .cpf(cpf)
                .rg(rg)
                .build();

        InteractorException interactorException = Assertions.assertThrows(InteractorException.class, () ->
                clientService.updateClient(cpf, client)
        );
        Assertions.assertEquals("Invalid CPF", interactorException.getMessage());
    }

    @Test
    void shouldFindClients() {

        when(clientCrudRepository.findClients())
                .thenReturn(Collections.singletonList(getClient()));

        List<Client> clientList = clientService.findClients();

        Assertions.assertNotNull(clientList);
        Assertions.assertEquals(1, clientList.size());
        Assertions.assertEquals(getClient().getCpf(), clientList.get(0).getCpf());
    }

    @Test
    void shouldFindClientByCpf() {

        when(clientCrudRepository.findClientByCpf(getClient().getCpf()))
                .thenReturn(getClient());

        Client clientFound = clientService.findClientByCpf(getClient().getCpf());
        Assertions.assertNotNull(clientFound);
        Assertions.assertEquals(getClient().getCpf(), clientFound.getCpf());
    }

    @Test
    void shouldSaveClient() {

        Client clientMock = getClient();
        when(clientCrudRepository.saveClient(clientMock))
                .thenReturn(clientMock);

        Client clientSaved = clientService.saveClient(clientMock);
        Assertions.assertNotNull(clientSaved);
        Assertions.assertEquals(clientMock.getCpf(), clientSaved.getCpf());
    }

    @Test
    void shouldUpdateClient() {
        Client clientMock = getClient();
        when(clientCrudRepository.updateClient(clientMock.getCpf(), clientMock))
                .thenReturn(clientMock);

        when(clientCrudRepository.findClientByCpf(clientMock.getCpf()))
                .thenReturn(clientMock);

        Client clientSaved = clientService.updateClient(clientMock.getCpf(), clientMock);
        Assertions.assertNotNull(clientSaved);
        Assertions.assertEquals(clientMock.getCpf(), clientSaved.getCpf());
    }

    private Client getClient() {
        return Client.builder()
                .id(1L)
                .cpf("00000000191")
                .rg("000000000")
                .age("26")
                .name("Random Name")
                .build();
    }
}
