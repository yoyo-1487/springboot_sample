package com.example.menu.controller.request.user;

import lombok.Data;

@Data
public class DelOrderCommand {

  private String username;
  private String item;
}