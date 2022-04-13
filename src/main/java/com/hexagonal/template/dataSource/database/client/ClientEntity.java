package com.hexagonal.template.dataSource.database.client;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence_generator")
  @SequenceGenerator(name = "client_sequence_generator", sequenceName = "client_sequence", allocationSize = 1)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "age", nullable = false)
  private String age;

  @Column(name = "cpf", nullable = false)
  private String cpf;

  @Column(name = "rg", nullable = false)
  private String rg;
}
