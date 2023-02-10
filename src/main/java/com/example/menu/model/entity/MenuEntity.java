package com.example.menu.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="menu_table")
@Getter
@Setter
@ToString
public class MenuEntity {
    @Id
    @Column
    private String items;

    @Column
    private Integer price;
}
