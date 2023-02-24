package com.example.menu.controller;

import com.example.menu.controller.request.user.InsertAccountPasswordCommand;
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

@Controller
public class LoginController {

  @Autowired
  LoginService loginService;
  @Autowired
  private LoginDao loginDao;

  @GetMapping({"/", "/login"})
  public String login(Model model) {
    model.addAttribute("insertAccountPassword", new InsertAccountPasswordCommand());
    return "login/Login";
  }

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("registerNewAccount", new InsertAccountPasswordCommand());
    return "register/Register";
  }

  @GetMapping("/registerSave")
  public String registerSave(@ModelAttribute InsertAccountPasswordCommand registerNewAccount) {
    return loginService.registerSave(registerNewAccount);
  }


  @GetMapping("/home")
  public String home(
      @ModelAttribute InsertAccountPasswordCommand insertAccountPassword,
      Model model,
      HttpServletRequest request
  ) {
    AccountEntity accountEntity = new AccountEntity();

    HttpSession session = request.getSession();
    String account = insertAccountPassword.getAccount();
    String password = insertAccountPassword.getPassword();
    //-----------判斷傳入值是否為空----------
    if (account != null || password != null) {//如果不為空
      accountEntity = loginService.findUsername(account, password);
      //存到session
      session.setAttribute("name", account);
      session.setAttribute("pwd", password);
    } else {//如果為空
      //從session撈資料
      accountEntity = loginService.findUsername(session.getAttribute("name").toString(),
          session.getAttribute("pwd").toString());
    }

    //----------判斷帳號密碼是否正確----------
    if (accountEntity == null) {
      return "login/LoginError";
    } else {
      model.addAttribute("showAccountPassword", accountEntity);
      return "menu/Home";
    }
  }

}
