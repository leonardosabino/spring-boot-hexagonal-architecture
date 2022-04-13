package com.hexagonal.template.transportLayer;

import com.hexagonal.template.entity.client.Client;
import com.hexagonal.template.transportLayer.http.client.ClientMapper;
import com.hexagonal.template.transportLayer.openapi.model.ClientDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
class MapStructServiceTest {

  @Test
  void shouldConverterDtoToEntity() {
    ClientDTO clientDTO = new ClientDTO();
    clientDTO.setCpf("00000000191");
    clientDTO.setRg("000000001");
    clientDTO.setName("Random name");
    clientDTO.setAge("18");

    Client client = ClientMapper.INSTANCE.clientDTOToClient(clientDTO);

    Assertions.assertEquals(client.getCpf(), clientDTO.getCpf());
    Assertions.assertEquals(client.getAge(), clientDTO.getAge());
    Assertions.assertEquals(client.getName(), clientDTO.getName());
    Assertions.assertEquals(client.getRg(), clientDTO.getRg());
  }

  @Test
  void shouldConverterEntityToDto() {
    Client client = new Client();
    client.setCpf("00000000191");
    client.setRg("000000001");
    client.setName("Random name");
    client.setAge("18");

    ClientDTO clientDTO = ClientMapper.INSTANCE.clientToClientDTO(client);

    Assertions.assertEquals(client.getCpf(), clientDTO.getCpf());
    Assertions.assertEquals(client.getAge(), clientDTO.getAge());
    Assertions.assertEquals(client.getName(), clientDTO.getName());
    Assertions.assertEquals(client.getRg(), clientDTO.getRg());
  }
}
