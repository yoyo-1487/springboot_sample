package com.example.menu.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="order_table")
public class OrderEntity {
    @Id
    @Column(name = "order_id")
    private String orderId;
    @Column
    private String orderItem;
    @Column
    private Integer orderPrice;
}

