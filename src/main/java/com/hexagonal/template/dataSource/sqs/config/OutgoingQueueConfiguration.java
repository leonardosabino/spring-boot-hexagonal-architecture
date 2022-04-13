package com.hexagonal.template.dataSource.sqs.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.QueueAttributeName;
import com.hexagonal.template.dataSource.sqs.OutgoingQueue;
import com.hexagonal.template.dataSource.sqs.QueueCommonFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@ConditionalOnProperty(value = {"cloud.aws.sqs.enable"}, havingValue = "true", matchIfMissing = false)
@Configuration
public class OutgoingQueueConfiguration {

  @Autowired
  private OutgoingQueue outgoingQueue;

  @Autowired
  private AmazonSQSAsync amazonSQSAsync;

  @PostConstruct
  public void createQueues() {
    if (amazonSQSAsync != null) {
      OutgoingQueue.getInstance().setUrl(createQueue(outgoingQueue));
    }
  }

  private String createQueue(final QueueCommonFields queue) {
    ListQueuesResult queueList = amazonSQSAsync.listQueues(queue.getName());
    if (!CollectionUtils.isEmpty(queueList.getQueueUrls())){
      return queueList.getQueueUrls().get(0);
    }

    Map<String, String> queueAttributes = new HashMap<>();
    queueAttributes.put(QueueAttributeName.FifoQueue.toString(), queue.getFifo());

    CreateQueueRequest createFifoQueueRequest = new CreateQueueRequest(
        queue.getName()).withAttributes(queueAttributes);

    return amazonSQSAsync.createQueue(createFifoQueueRequest).getQueueUrl();
  }
}
