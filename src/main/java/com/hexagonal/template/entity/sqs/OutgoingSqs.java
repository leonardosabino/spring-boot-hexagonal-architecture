package com.hexagonal.template.entity.sqs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OutgoingSqs {
  private String message;
}
