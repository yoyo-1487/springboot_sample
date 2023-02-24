package com.example.menu.service;

import com.example.menu.aggregate.OrderAggregate;
import com.example.menu.controller.request.user.AddInformationToMenuCommand;
import com.example.menu.model.dao.OrderDao;
import com.example.menu.model.entity.OrderEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

  @Autowired
  OrderAggregate orderAggregate;
  @Autowired
  private OrderDao orderDao;

  //------------得到訂單資料(項目,數量)------------
  public HashMap<String, Integer> getOrderItemAndNumber(String username) {
    List<OrderEntity> orderListAll = orderAggregate.getOrderAll(username);//1.撈出訂單所有資料
    return orderAggregate.findObjectAndCountAmount(orderListAll);//2.找出訂單有哪些種類,並累加數量
  }

  //
  public Integer countPrice(String username) {
    List<OrderEntity> orderListAll = orderAggregate.getOrderAll(username);//1.撈出訂單所有資料
    return orderAggregate.countPrice(orderListAll);
  }

  //
  public AddInformationToMenuCommand setUser(String username, HttpSession session) {
    //判斷傳入質是否為空,並設定Username
    return orderAggregate.isNull(username, session);
  }


  public List<OrderEntity> chooseOrder(String username) {
    return orderAggregate.getOrderAll(username);
  }

  public void selectIdByUsernameAndItem(String username, String item) {
    orderAggregate.selectIdByUsernameAndItem(username, item);
  }


}
