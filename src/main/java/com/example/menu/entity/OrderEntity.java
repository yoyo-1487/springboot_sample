package com.example.menu.entity;

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
    private String order_id;
    @Column
    private String order_item;
    @Column
    private Integer order_price;
}

