package com.example.menu.aggregate;

import com.example.menu.controller.request.user.AddInformationToMenuCommand;
import com.example.menu.model.dao.OrderDao;
import com.example.menu.model.entity.OrderEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class OrderAggregate {

  @Autowired
  OrderDao orderDao;

  /**
   * ------------ 撈出訂單所有資料
   *
   * @return 菜單
   */
  public List<OrderEntity> getOrderAll(String username) {
    return orderDao.findByUsername(username);

  }

  public void selectIdByUsernameAndItem(String username, String item) {
    List<String> delOrder = orderDao.selectIdByUsernameAndItem(username, item);
    orderDao.deleteById(delOrder.get(0));
  }

  /**
   * ------------ 回傳訂單的細節
   *
   * @param orderListAll 訂單Table的資料
   * @return 訂單計算結果
   */
  public HashMap<String, Integer> findObjectAndCountAmount(List<OrderEntity> orderListAll) {
    //TODO for 改成 lambda
    HashMap<String, Integer> itemsMap = new HashMap<>();

    orderListAll.forEach(orderEntity -> {
      String orderItem = orderEntity.getOrderItem(); //---取出菜單的名稱
      if (itemsMap.containsKey(orderItem)) { //---如果菜單已經存在我的集合，那就把它原本的數量做+1
        itemsMap.put(orderItem, itemsMap.get(orderItem) + 1);
      } else { //---第一次找到，就做初始化的動作
        itemsMap.put(orderItem, 1);
      }
    });

//    for (OrderEntity orderEntity : orderListAll) { //---撈出訂單table的菜單
//      String orderItem = orderEntity.getOrderItem(); //---取出菜單的名稱
//      if (itemsMap.containsKey(orderItem)) { //---如果菜單已經存在我的集合，那就把它原本的數量做+1
//        itemsMap.put(orderItem, itemsMap.get(orderItem) + 1);
//      } else { //---第一次找到，就做初始化的動作
//        itemsMap.put(orderItem, 1);
//      }
//    }
    return itemsMap;
  }

  /**
   * ------------ 計算總金額
   *
   * @param orderListAll 訂單Table的資料
   * @return 總金額
   */
  public Integer countPrice(List<OrderEntity> orderListAll) {
    int totalprice = 0;
    for (OrderEntity orderentity : orderListAll) {
      totalprice += orderentity.getOrderPrice();
    }
    return totalprice;
  }

  //
  public AddInformationToMenuCommand isNull(String username, HttpSession session) {
    AddInformationToMenuCommand addInformationToMenuCommand = new AddInformationToMenuCommand();//點餐資訊
    if (username != null) {
      session.setAttribute("name", username);//存到session
      addInformationToMenuCommand.setUsername(username);//設定哪位使用者點餐
    } else {//如果為空
      //從session撈資料
      addInformationToMenuCommand.setUsername(session.getAttribute("name").toString());//設定哪位使用者點餐
    }
    return addInformationToMenuCommand;
  }


}
