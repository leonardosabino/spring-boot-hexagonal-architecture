package com.hexagonal.template.transportLayer.sqs;

import com.hexagonal.template.entity.sqs.IncomingSqs;
import com.hexagonal.template.interactor.sqs.MessageUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
@ConditionalOnProperty(value = {"cloud.aws.sqs.enable"}, havingValue = "true")
public class ConsumerSqsListener {

  @Autowired
  private final IncomingQueue incomingQueue;

  @Autowired
  private final MessageUseCase messageUseCase;

  @SqsListener(value = {
      "${cloud.aws.sqs.incoming-queue.name}"}, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void receiveMessage(IncomingSqs message, @Header("MessageId") String messageId) {
    log.info("SQS - Message received: {} with id: {}", incomingQueue.getName(), messageId);
    log.info("SQS - Message payload: {} ", message);

    messageUseCase.save(message);
  }

}
