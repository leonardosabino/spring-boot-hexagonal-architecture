package com.hexagonal.template.dataSource.restclient;

public class FeignException extends RuntimeException {

  public FeignException(String message) {
    super(message);
  }
}
