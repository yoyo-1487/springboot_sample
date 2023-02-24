package com.example.menu.controller.request.admin;

import lombok.Data;

@Data
public class AdminLoginCommand {
  private String adminName;
  private String adminPwd;
}
