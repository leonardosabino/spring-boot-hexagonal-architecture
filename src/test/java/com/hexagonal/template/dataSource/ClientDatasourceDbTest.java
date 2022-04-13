package com.hexagonal.template.dataSource;

import com.hexagonal.template.dataSource.database.client.ClientDatasourceDb;
import com.hexagonal.template.entity.client.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.util.List;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class ClientDatasourceDbTest {

  @Autowired
  private ClientDatasourceDb clientDatasourceSqlServer;

  @Test
  @Order(1)
  void shouldSaveClientWithSuccess(){
    Client client = getClient();
    String expectedName = "Random name";

    clientDatasourceSqlServer.saveClient(client);

    Client clientFound = clientDatasourceSqlServer.findClientByCpf(client.getCpf());

    Assertions.assertNotNull(clientFound);
    Assertions.assertNotNull(clientFound.getId());
    Assertions.assertEquals(clientFound.getName(), expectedName);
  }

  @Test
  @Order(2)
  void shouldUpdateClientWithSuccess(){
    Client client = getClient();
    String cpfToUpdate = client.getCpf();
    String newCpf = "00000000000";
    client.setCpf(newCpf);

    clientDatasourceSqlServer.updateClient(cpfToUpdate, client);

    Client clientFound = clientDatasourceSqlServer.findClientByCpf(client.getCpf());

    Assertions.assertNotNull(clientFound);
    Assertions.assertNotNull(clientFound.getId());
    Assertions.assertEquals(clientFound.getCpf(), newCpf);
  }

  @Test
  @Order(3)
  void shouldReturnNullWhenCpfIsNotFound(){
    Client clientFound = clientDatasourceSqlServer.findClientByCpf("99999999999");
    Assertions.assertNull(clientFound);
  }

  @Test
  @Order(4)
  void shouldDeleteClientWithSuccess(){
    Client client = getClient();
    client.setCpf("33333333333");
    clientDatasourceSqlServer.saveClient(client);

    Client clientFound = clientDatasourceSqlServer.findClientByCpf("33333333333");
    Assertions.assertNotNull(clientFound);
    Assertions.assertNotNull(clientFound.getId());

    clientDatasourceSqlServer.deleteClient(clientFound);

    clientFound = clientDatasourceSqlServer.findClientByCpf("33333333333");
    Assertions.assertNull(clientFound);
  }

  @Test
  @Order(5)
  void shouldFindAllClients(){
    Client client1 = getClient();
    Client client2 = getClient();

    client1.setId(null);
    client1.setCpf("88888888888");
    client2.setId(null);
    client2.setCpf("77777777777");

    clientDatasourceSqlServer.saveClient(client1);
    clientDatasourceSqlServer.saveClient(client2);

    List<Client> clientList = clientDatasourceSqlServer.findClients();

    Assertions.assertFalse(CollectionUtils.isEmpty(clientList));
    Assertions.assertTrue(clientList.size() >= 2);
    Assertions.assertTrue(
            clientList.stream().anyMatch(client -> client.getCpf().equals(client1.getCpf()))
    );
    Assertions.assertTrue(
            clientList.stream().anyMatch(client -> client.getCpf().equals(client2.getCpf()))
    );
  }

  private Client getClient() {
    return Client.builder()
            .id(1L)
            .cpf("00000000191")
            .rg("000000000")
            .age("26")
            .name("Random name")
            .build();
  }

}

