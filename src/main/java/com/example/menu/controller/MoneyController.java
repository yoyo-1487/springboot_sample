package com.example.menu.controller;

import com.example.menu.controller.request.test.InputIdPwd;
import com.example.menu.controller.request.test.SaveMoney;
import com.example.menu.controller.request.test.delMoney;
import com.example.menu.model.dao.MoneyDao;
import com.example.menu.model.entity.MoneyEntity;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
public class MoneyController {

  @Autowired
  MoneyDao moneyDao;

  @GetMapping("/loginn")
  public String login(@RequestBody InputIdPwd command) {
    Optional<MoneyEntity> moneyEntity = moneyDao.findById(command.getId());
    if (moneyEntity.isEmpty() ||
        !moneyEntity.get().getId().equals(command.getId()) ||
        !moneyEntity.get().getPassword().equals(command.getPassword())
    ) {
      return "帳密錯誤";
    } else {
      return "成功登入";
    }
  }

  @GetMapping("/saveMoney")
  public String saveMoney(@RequestBody SaveMoney command) {
    Optional<MoneyEntity> moneyEntity = moneyDao.findById(command.getId());
    Integer money = moneyEntity.get().getMoney();
    money = money + command.getSaveMoney();
    MoneyEntity moneyEntity1 = moneyEntity.get();
    moneyEntity1.setMoney(money);
    moneyDao.save(moneyEntity1);
    return "存錢成功,餘額=" + moneyDao.findById(command.getId()).get().getMoney();
  }

  @Transactional
  @GetMapping("/delMoney")
  public String delMoney(@RequestBody delMoney command) {
    Optional<MoneyEntity> moneyEntity = moneyDao.findById(command.getId());
    Integer money = moneyEntity.get().getMoney();
    if (command.getDelMoney() > money) {
      return "餘額不足";
    } else {
      money = money - command.getDelMoney();
      MoneyEntity moneyEntity1 = moneyEntity.get();
      moneyEntity1.setMoney(money);
      moneyDao.save(moneyEntity1);
      return "領錢成功,餘額=" + moneyDao.findById(command.getId()).get().getMoney();
    }

  }


}
