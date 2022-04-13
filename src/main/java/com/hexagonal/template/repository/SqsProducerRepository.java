package com.hexagonal.template.repository;

public interface SqsProducerRepository {

  <T> void send(T outgoing);

}
