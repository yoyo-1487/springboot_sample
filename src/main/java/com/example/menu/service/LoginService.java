package com.example.menu.service;

import com.example.menu.aggregate.LoginAggregate;
import com.example.menu.controller.request.InsertAccountPasswordCommand;
import com.example.menu.model.entity.AccountEntity;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  @Autowired
  private LoginAggregate loginAggregate;

  public AccountEntity findUsername(String username, String password) {
    return loginAggregate.findUsername(username, password);
  }

  public String registerSave(InsertAccountPasswordCommand registerNewAccount) {
    return loginAggregate.registerErr(registerNewAccount,
        loginAggregate.findAccount(registerNewAccount.getAccount()));
  }
}
