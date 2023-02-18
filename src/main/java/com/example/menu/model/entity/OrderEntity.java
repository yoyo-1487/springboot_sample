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
  @Column(name = "order_item")
  private String orderItem;
  @Column(name = "order_price")
  private Integer orderPrice;
  @Column(name = "order_username")
  private String orderUsername;
}

