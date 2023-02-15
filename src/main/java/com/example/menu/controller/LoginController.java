package com.example.menu.controller;

import com.example.menu.controller.request.AddInformationToMenuCommand;
import com.example.menu.controller.request.InsertAccountandPasswordToLoginCommand;
import com.example.menu.model.entity.AccountEntity;
import com.example.menu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

  @Autowired
  LoginService loginService;

  @GetMapping({"/", "/login"})
  public String login(Model model) {
    model.addAttribute("insertAccountPassword", new InsertAccountandPasswordToLoginCommand());
    return "login/Login";
  }

//  @PostMapping("/logincheck")
//  public String
//  logincheck(@ModelAttribute InsertAccountandPasswordToLoginCommand insertAccountPassword,
//      Model model) {
//    model.addAttribute("insertAccountPassword",
//        loginService.findUsername(insertAccountPassword.getAccount(),
//            insertAccountPassword.getPassword()));
//    return "login/LoginShow";
//  }

  @PostMapping("/home")
  public String home(@ModelAttribute InsertAccountandPasswordToLoginCommand insertAccountPassword,
      Model model) {

    AccountEntity accountEntity = loginService.findUsername(insertAccountPassword.getAccount(),
        insertAccountPassword.getPassword());
    if (accountEntity == null) {
      System.out.println("null");
      return "login/LoginShow";
    } else {
      System.out.println("not null");
      model.addAttribute("insertAccountPassword", accountEntity);
      return "menu/Home";
    }


  }


}
