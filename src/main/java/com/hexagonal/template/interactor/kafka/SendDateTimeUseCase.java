package com.hexagonal.template.interactor.kafka;

import com.hexagonal.template.repository.KafkaProducerRepository;
import org.springframework.stereotype.Service;

@Service
public class SendDateTimeUseCase {

  private final KafkaProducerRepository kafkaProducerRepository;

  public SendDateTimeUseCase(KafkaProducerRepository kafkaProducerRepository) {
    this.kafkaProducerRepository = kafkaProducerRepository;
  }

  public String execute() {
    return kafkaProducerRepository.sendDateTime();
  }

}
