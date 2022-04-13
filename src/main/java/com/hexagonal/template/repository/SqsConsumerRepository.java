package com.hexagonal.template.repository;

import com.hexagonal.template.entity.sqs.IncomingSqs;

public interface SqsConsumerRepository {

  void save(IncomingSqs message);

}
