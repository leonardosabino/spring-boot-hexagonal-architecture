package com.hexagonal.template.dataSource.kafka;

import com.hexagonal.template.repository.KafkaProducerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ProducerKafkaDataSource implements KafkaProducerRepository {

  @Autowired(required = false)
  private KafkaTemplate<String, String> kafkaTemplate;

  @Value(value = "${kafka.topic}")
  private String kafkaTopic;

  @Value(value = "${kafka.enable}")
  private boolean kafkaEnable;

  @Override
  public String sendDateTime() {
    if (!kafkaEnable) {
      throw new IllegalArgumentException(
          "Kafka is not enable: " +
              "kafka.enable is false" +
              "origin");
    }
    var dateTime = LocalDateTime.now().toString();
    kafkaTemplate.send(kafkaTopic, dateTime);
    log.info("Send message: " + dateTime);
    return dateTime;
  }
}
