package com.example.menu.controller.request;

import lombok.Data;

@Data
public class AddInformationToMenuCommand {

  private String items;
  private String price;
}
