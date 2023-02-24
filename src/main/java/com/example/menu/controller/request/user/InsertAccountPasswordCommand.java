package com.example.menu.controller.request.user;

import lombok.Data;

@Data
public class InsertAccountPasswordCommand {

  private String account;
  private String password;
  private String passwordCheck;
}
