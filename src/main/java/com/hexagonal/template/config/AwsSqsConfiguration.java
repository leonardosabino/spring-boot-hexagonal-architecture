package com.hexagonal.template.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
@EnableSqs
@ConditionalOnProperty(value = {"cloud.aws.sqs.enable"}, havingValue = "true")
public class AwsSqsConfiguration {

  @Value(value = "${cloud.aws.host}")
  public String sqsUrl;

  @Value(value = "${cloud.aws.access-key}")
  public String awsAccessKey;

  @Value(value = "${cloud.aws.secret-key}")
  public String awsSecretKey;

  @Primary
  @Bean
  public AmazonSQSAsync amazonSQSAsync() {
    return AmazonSQSAsyncClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(
            new BasicAWSCredentials(awsAccessKey, awsSecretKey)
        ))
        .withEndpointConfiguration(
            new AwsClientBuilder.EndpointConfiguration(sqsUrl,
                Regions.DEFAULT_REGION.getName()))
        .build();
  }

}
