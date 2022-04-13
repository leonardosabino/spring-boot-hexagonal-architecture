package com.hexagonal.template.transportLayer.http.client;

import com.hexagonal.template.entity.client.Client;
import com.hexagonal.template.transportLayer.openapi.model.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO clientToClientDTO(Client client);

    Client clientDTOToClient(ClientDTO clientDTO);

    List<ClientDTO> clientsToClientsDTO(List<Client> client);
}

