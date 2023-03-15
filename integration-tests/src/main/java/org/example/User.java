package org.example;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Getter
public class User {
  @Id
  private Long id;
  private String name;
}
