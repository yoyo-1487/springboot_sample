package com.example.menu.controller;

import com.example.menu.controller.request.InsertAccountandPasswordToLoginCommand;
import com.example.menu.model.dao.LoginDao;
import com.example.menu.model.entity.AccountEntity;
import com.example.menu.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
  @Autowired
  private LoginDao loginDao;

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

  @GetMapping("/home")
  public String home(
      @ModelAttribute InsertAccountandPasswordToLoginCommand insertAccountPassword,
      Model model,
      HttpServletRequest request
  ) {
    AccountEntity accountEntity = new AccountEntity();
    HttpSession session = request.getSession();
    String account = insertAccountPassword.getAccount();
    String password = insertAccountPassword.getPassword();

    if (account != null || password != null) {//如果不為空
      accountEntity = loginService.findUsername(account,
          password);
      //存到session
      session.setAttribute("name", account);
      session.setAttribute("pwd", password);
    } else {//如果為空
      accountEntity = loginService.findUsername(session.getAttribute("name").toString(),
          session.getAttribute("pwd").toString());
    }
//    System.out.println("session = " + session.getAttribute("name"));
//    System.out.println("session = " + session.getAttribute("pwd"));
    if (accountEntity == null) {
      return "login/LoginShow";
    } else {
      System.out.println(accountEntity);
      model.addAttribute("showAccountPassword", accountEntity);
      return "menu/Home";
    }
  }

}
