package com.hexagonal.template.dataSource.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexagonal.template.repository.SqsProducerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class ProducerSqsDataSource implements SqsProducerRepository {

  @Autowired(required = false)
  private AmazonSQS sqsClient;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired(required = false)
  private OutgoingQueue outgoingQueue;

  @Value(value = "${cloud.aws.sqs.enable}")
  private Boolean sqsEnable;

  @Override
  public <T> void send(T outgoing) {
    if (sqsClient == null && !sqsEnable) {
      throw new SqsException("SQS is not enable: cloud.aws.sqs.enable is false");
    }

    log.info("SQS - Sending message to queue: {}", outgoingQueue.getName());

    try {
      var body = objectMapper.writeValueAsString(outgoing);

      SendMessageRequest request = new SqsMessageRequestBuilder()
          .withBody(body)
          .withQueueUrl(OutgoingQueue.getUrl())
          .build();

      Optional.ofNullable(sqsClient).ifPresent(amazonSQS -> sqsClient.sendMessage(request));

      log.info(body + " " + "was sent to the queue");

    } catch (Exception e) {
      throw new SqsException("SQS - Fail to send message");
    }
  }
}
