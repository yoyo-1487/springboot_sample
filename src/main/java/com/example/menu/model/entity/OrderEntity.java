package com.example.menu.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_table")
public class OrderEntity {

  @Id
  @Column(name = "order_id")
  private String orderId;
  @Column
  private String orderItem;
  @Column
  private Integer orderPrice;
}

