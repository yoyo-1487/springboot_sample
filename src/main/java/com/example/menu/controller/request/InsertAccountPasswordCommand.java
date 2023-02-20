package com.example.menu.controller.request;

import lombok.Data;

@Data
public class InsertAccountPasswordCommand {

  private String account;
  private String password;
  private String passwordCheck;
}
