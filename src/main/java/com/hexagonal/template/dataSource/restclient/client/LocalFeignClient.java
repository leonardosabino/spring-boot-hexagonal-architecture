package com.hexagonal.template.dataSource.restclient.client;

import com.hexagonal.template.entity.client.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "localClient", url = "${restclient.mock.url}")
interface LocalFeignClient {

  @GetMapping("/v1/client")
  ResponseEntity<List<Client>> findClients();

  @PostMapping("/v1/client")
  ResponseEntity<Client> saveClient(Client client);

  @PutMapping("/v1/client")
  ResponseEntity<Client> updateClient(Client client);

  @DeleteMapping("/v1/client/{cpf}")
  void deleteClient(@PathVariable String cpf);

  @DeleteMapping("/v1/client/{cpf}")
  ResponseEntity<Client> FindClientByCpf(@PathVariable String cpf);
}
