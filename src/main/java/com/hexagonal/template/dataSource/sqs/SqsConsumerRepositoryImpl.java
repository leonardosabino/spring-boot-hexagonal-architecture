package com.hexagonal.template.dataSource.sqs;

import com.hexagonal.template.entity.sqs.IncomingSqs;
import com.hexagonal.template.repository.SqsConsumerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SqsConsumerRepositoryImpl implements SqsConsumerRepository {

  @Override
  public void save(IncomingSqs message) {
    log.info("Saving message: {}", message.getMessage());
  }
}
