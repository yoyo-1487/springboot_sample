package com.example.menu.controller.request.user;

import lombok.Data;

@Data
public class AddInformationToMenuCommand {

  private String items;
  private String price;
  private String username;
}
