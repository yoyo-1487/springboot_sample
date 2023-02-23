package com.example.menu.aggregate;

import com.example.menu.controller.request.InsertAccountPasswordCommand;
import com.example.menu.model.dao.LoginDao;
import com.example.menu.model.entity.AccountEntity;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginAggregate {

  @Autowired
  LoginDao loginDao;

  /**
   * 判斷是否有此號，並確認密碼
   *
   * @param username
   * @param password
   * @return
   */
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
    } else {
      return true;
    }
  }

  public String registerErr(InsertAccountPasswordCommand registerNewAccount, boolean findAccount) {
    if (registerNewAccount.getAccount().isEmpty() || registerNewAccount.getPassword()
        .isEmpty()) {//帳密為空
      return "register/RegisterEmpty";
    } else {
      if (findAccount == false) {//帳號存在
        return "register/RegisterError";
      } else {
        if (!registerNewAccount.getPassword()
            .equals(registerNewAccount.getPasswordCheck())) {//輸入密碼要相同
          return "register/PasswordCheckErr";
        } else {
          AccountEntity accountEntity = new AccountEntity();
          accountEntity.setUsername(registerNewAccount.getAccount());
          accountEntity.setPassword(registerNewAccount.getPassword());
          loginDao.save(accountEntity);
          return "register/RegisterSuc";
        }
      }
    }
  }

}
