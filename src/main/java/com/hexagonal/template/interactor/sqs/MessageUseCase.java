package com.hexagonal.template.interactor.sqs;

import com.hexagonal.template.entity.sqs.IncomingSqs;
import com.hexagonal.template.entity.sqs.OutgoingSqs;
import com.hexagonal.template.repository.SqsConsumerRepository;
import com.hexagonal.template.repository.SqsProducerRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageUseCase {

  private final SqsProducerRepository sqsProducerRepository;
  private final SqsConsumerRepository sqsConsumerRepository;

  public MessageUseCase(SqsProducerRepository sqsProducerRepository,
                        SqsConsumerRepository sqsConsumerRepository) {
    this.sqsProducerRepository = sqsProducerRepository;
    this.sqsConsumerRepository = sqsConsumerRepository;
  }

  public void send(OutgoingSqs message) {
    sqsProducerRepository.send(message);
  }

  public void save(IncomingSqs message) {
    sqsConsumerRepository.save(message);
  }
}
