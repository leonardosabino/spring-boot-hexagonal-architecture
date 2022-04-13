package com.hexagonal.template.transportLayer.http.client;

import com.hexagonal.template.interactor.client.ClientUseCase;
import com.hexagonal.template.transportLayer.openapi.api.ClientApi;
import com.hexagonal.template.transportLayer.openapi.model.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "/v1")
public class ClientController implements ClientApi {

    @Autowired
    private ClientUseCase clientUseCase;

    @Override
    public ResponseEntity<ClientDTO> findClientByCpf(String cpf) {
        return ResponseEntity.ok(
                ClientMapper.INSTANCE.clientToClientDTO(
                        clientUseCase.findClientByCpf(cpf)
                )
        );
    }

    @Override
    public ResponseEntity<List<ClientDTO>> findClients() {
        return ResponseEntity.ok(
                ClientMapper.INSTANCE.clientsToClientsDTO(
                        clientUseCase.findClients()
                )
        );
    }

    @Override
    public ResponseEntity<ClientDTO> saveClient(ClientDTO clientDTO) {

        var client = clientUseCase.saveClient(ClientMapper.INSTANCE.clientDTOToClient(clientDTO));
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(location).body(ClientMapper.INSTANCE.clientToClientDTO(client));

    }

    @Override
    public ResponseEntity<Void> updateClient(String cpf, ClientDTO clientDTO) {
        ClientMapper.INSTANCE.clientToClientDTO(
                clientUseCase.updateClient(cpf,
                        ClientMapper.INSTANCE.clientDTOToClient(clientDTO)
                )
        );

        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Void> deleteClient(String cpf) {
        clientUseCase.deleteClient(cpf);
        return ResponseEntity.noContent().build();
    }
}
