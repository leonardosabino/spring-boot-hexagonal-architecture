package com.hexagonal.template.entity.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

  private Long id;

  private String name;

  private String age;

  private String cpf;

  private String rg;

  public boolean isValid() {
    return cpf != null && cpf.length() == 11 && StringUtils.isNumeric(cpf);
  }
}
