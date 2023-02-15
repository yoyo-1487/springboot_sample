package com.example.menu.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "account_table")
@Data
public class AccountEntity {

  @Id
  @Column
  private String username;

  @Column
  private String password;
}
