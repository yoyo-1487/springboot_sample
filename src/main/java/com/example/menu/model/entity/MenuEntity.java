package com.example.menu.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "menu_table")
@Data
public class MenuEntity {

  @Id
  @Column
  private String items;

  @Column
  private Integer price;
}
