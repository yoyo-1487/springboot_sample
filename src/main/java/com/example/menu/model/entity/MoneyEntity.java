package com.example.menu.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "money")
public class MoneyEntity {

  @Id
  @Column(name = "id")
  private Integer id;
  @Column(name = "password")
  private String password;
  @Column(name = "money")
  private Integer money;
}