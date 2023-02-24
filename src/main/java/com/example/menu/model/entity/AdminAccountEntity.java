package com.example.menu.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "admin_account")
@Data
public class AdminAccountEntity {

  @Id
  @Column(name = "admin_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "admin_name")
  private String adminName;

  @Column(name = "admin_pwd")
  private String adminPwd;
}
