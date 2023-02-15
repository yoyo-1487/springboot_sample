package com.example.menu.service;

import com.example.menu.aggregate.LoginAggregate;
import com.example.menu.model.dao.LoginDao;
import com.example.menu.model.entity.AccountEntity;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
  @Autowired
  private LoginAggregate loginAggregate;

  public AccountEntity findUsername(String username, String password){
    return loginAggregate.findUsername(username, password);
  }

}
