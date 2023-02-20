package com.example.menu.aggregate;

import com.example.menu.model.dao.LoginDao;
import com.example.menu.model.entity.AccountEntity;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginAggregate {

  @Autowired
  LoginDao loginDao;

  public AccountEntity findUsername(String username, String password) {
    Optional<AccountEntity> user = loginDao.findById(username);//找到User資料By Username
    if (user.isPresent()) {
      if (password.equals(user.get().getPassword())) {
        return user.get();
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  public boolean findAccount(String account) {
    Optional<AccountEntity> user = loginDao.findById(account);
    if (user.isPresent()) {
      return false;
    }else{
      return true;
    }
  }

  public void saveAccount(String username, String password) {
    AccountEntity accountEntity= new AccountEntity();
    accountEntity.setUsername(username);
    accountEntity.setPassword(password);
    loginDao.save(accountEntity);
  }

}
