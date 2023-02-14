package com.example.menu.controller.request;

import lombok.Data;

@Data
public class InsertAccountandPasswordToLoginCommand {

  private String account;
  private String password;
}
