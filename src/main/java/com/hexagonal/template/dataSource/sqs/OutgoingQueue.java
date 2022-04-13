package com.hexagonal.template.dataSource.sqs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = {"cloud.aws.sqs.enable"}, havingValue = "true", matchIfMissing = false)
@ConfigurationProperties(prefix ="cloud.aws.sqs.outgoing-queue")
public class OutgoingQueue extends QueueCommonFields {

  private static OutgoingQueue instance;

  public OutgoingQueue() {
    //Construtor vazio para o vequerido pelo spring
  }

  public static OutgoingQueue getInstance(){
    if (instance == null) {
      instance = new OutgoingQueue();
    }
    return instance;
  }

  public static String getUrl() {
    return instance != null ? instance.url : null;
  }

}
