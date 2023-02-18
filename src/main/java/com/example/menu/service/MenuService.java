package com.example.menu.service;

import com.example.menu.model.entity.MenuEntity;
import com.example.menu.aggregate.MenuAggregate;
import com.example.menu.model.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {

  @Autowired
  MenuAggregate menuaggregate;

  //------------拿到菜單------------
  public Iterable<MenuEntity> getMenuAll() {
    return menuaggregate.getMenuAll();//1.查詢菜單
  }

  //------------找菜單是否有此品項並點餐------------
  public OrderEntity findAndOrder(String item, String username) {
    Optional<MenuEntity> find_and_order = menuaggregate.findMenuByItem(item);//1.品項,find_and_order=輸入的品項
    return menuaggregate.existThenOrder(find_and_order, username);//2.判斷品項存在並加入
  }

}