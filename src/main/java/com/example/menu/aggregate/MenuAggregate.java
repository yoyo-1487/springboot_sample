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
    private int totalPrice=0;
    @Autowired
    MenuDao menuDao;
    @Autowired
    OrderDao orderDao;

    /**
     * 查詢菜單
     * @return 菜單品項
     */
    public Iterable<MenuEntity> getMenuAll() {
        return menuDao.findAll();
    }

    /**
     * 接值
     * @param item 品項名稱
     * @return 品項資料
     */
    public Optional<MenuEntity> findMenuByItem (String item){
        return menuDao.findById(item);
    }

    /**
     * 判斷品項存在並加入
     * @param item 輸入品項的資料
     * @return 成功點餐資訊
     */
    public String existThenOrder (Optional<MenuEntity> item){
        if(item.isEmpty()){
            return "不存在";
        }
        else{
            OrderEntity order = new OrderEntity();
            order.setOrderId(UUID.randomUUID().toString());
            order.setOrderItem(item.get().getItems());
            order.setOrderPrice(item.get().getPrice());
            orderDao.save(order);//-----將值寫入table_menu
            totalPrice = totalPrice + item.get().getPrice();//-----加總金額
            return "成功點餐,餐點為:" + item.get().getItems() + ",價格:" + item.get().getPrice();
        }
    }

    /**
     * 加總金額
     * @return 總金額
     */
    public Integer totalPrice(){
        return totalPrice;
    }

}
