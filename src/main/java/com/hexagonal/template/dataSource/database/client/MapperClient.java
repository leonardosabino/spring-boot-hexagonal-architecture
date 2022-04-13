package com.hexagonal.template.dataSource.database.client;

import com.hexagonal.template.entity.client.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperClient {

    MapperClient INSTANCE = Mappers.getMapper(MapperClient.class);

    Client clientEntityToClient(ClientEntity clientEntity);

    ClientEntity clientToClientEntity(Client client);
}
