package com.hexagonal.template.transportLayer.sqs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = {"cloud.aws.sqs.enable"}, havingValue = "true", matchIfMissing = false)
@ConfigurationProperties(prefix ="cloud.aws.sqs.incoming-queue")
public class IncomingQueue extends QueueCommonFields {

  private static IncomingQueue instance;

  public IncomingQueue() {
  }

  public static IncomingQueue getInstance(){
    if (instance == null) {
      instance = new IncomingQueue();
    }
    return instance;
  }

  public static String getUrl() {
    return instance != null ? instance.url : null;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
