package com.example.menu.aggregate;

import com.example.menu.model.dao.MenuDao;
import com.example.menu.model.dao.OrderDao;
import com.example.menu.model.entity.MenuEntity;
import com.example.menu.model.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MenuAggregate {

  @Autowired
  MenuDao menuDao;
  @Autowired
  OrderDao orderDao;

  /**
   * ------------ 查詢菜單
   *
   * @return 菜單品項
   */
  public Iterable<MenuEntity> getMenuAll() {
    return menuDao.findAll();
  }

  /**
   * ------------ 用菜名找菜單資料
   *
   * @param item 品項名稱
   * @return 品項資料
   */
  public Optional<MenuEntity> findMenuByItem(String item) {
    return menuDao.findById(item);
  }

  /**
   * ------------ 判斷品項存在並加入
   *
   * @param item 輸入品項的資料
   * @return 成功點餐資訊
   */
  public OrderEntity existThenOrder(Optional<MenuEntity> item, String username) {
    if (item.isEmpty()) {
      return null;
    } else {
      OrderEntity order = new OrderEntity();
      order.setOrderId(UUID.randomUUID().toString());
      order.setOrderItem(item.get().getItems());
      order.setOrderPrice(item.get().getPrice());
      order.setOrderUsername(username);
      orderDao.save(order);//-----將值寫入table_menu
      return order;
      //return "成功點餐,餐點為:" + item.get().getItems() + ",價格:" + item.get().getPrice();
    }
  }

}
