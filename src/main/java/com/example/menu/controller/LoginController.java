package com.example.menu.controller;

import com.example.menu.controller.request.AddInformationToMenuCommand;
import com.example.menu.controller.request.InsertAccountandPasswordToLoginCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

  @GetMapping({"/", "/login"})
  public String login(Model model) {
    InsertAccountandPasswordToLoginCommand insertAccountPassword = new InsertAccountandPasswordToLoginCommand();
    model.addAttribute("insertAccountPassword", insertAccountPassword);
    return "login/Login";
  }

  @PostMapping("/home")
  public String home(@ModelAttribute InsertAccountandPasswordToLoginCommand insertAccountPassword,
      Model model) {
    model.addAttribute("insertAccountPassword", insertAccountPassword);
    return "menu/Home";
  }
}
