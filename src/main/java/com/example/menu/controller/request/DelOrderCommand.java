package com.example.menu.controller.request;

import lombok.Data;

@Data
public class DelOrderCommand {

  private String username;
  private String item;
}