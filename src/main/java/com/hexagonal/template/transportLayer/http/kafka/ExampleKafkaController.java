package com.hexagonal.template.transportLayer.http.kafka;

import com.hexagonal.template.interactor.kafka.SendDateTimeUseCase;
import com.hexagonal.template.transportLayer.openapi.api.KafkaApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(
    path = "kafka"
)
public class ExampleKafkaController implements KafkaApi {

  @Autowired
  private SendDateTimeUseCase sendDateTimeUseCase;

  @Override
  public ResponseEntity<String> sendMessageExample() {
    return ResponseEntity.ok(sendDateTimeUseCase.execute()
    );
  }
}