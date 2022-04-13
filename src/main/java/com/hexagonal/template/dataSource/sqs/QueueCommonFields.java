package com.hexagonal.template.dataSource.sqs;

public abstract class QueueCommonFields {
  private String name;
  private String fifo;
  protected String url;

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFifo() {
    return fifo;
  }
}
