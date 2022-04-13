package com.hexagonal.template.dataSource.sqs;

public class SqsException extends RuntimeException {

  public SqsException(String message) {
    super(message);
  }
}
