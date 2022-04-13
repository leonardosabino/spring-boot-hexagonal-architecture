package com.hexagonal.template.transportLayer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = {"kafka.enable"}, havingValue = "true")
@Slf4j
public class ConsumerKafkaListener {

  @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupId}")
  public void readeKafkaMessages(String message) {
    log.info("Message received: " + message);
  }

}
