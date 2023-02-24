package com.example.menu.controller.request.admin;

import lombok.Data;

@Data
public class AddItemPriceIntoMenu {
  private String item;
  private Integer price;
}
