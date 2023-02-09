package com.example.menu.aggregate;

import com.example.menu.dao.MenuDao;
import com.example.menu.dao.OrderDao;
import com.example.menu.entity.MenuEntity;
import com.example.menu.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MenuAggregate {
    private int Totalprice=0;
    @Autowired
    MenuDao menuDao;
    @Autowired
    OrderDao orderDao;
    //------------查詢菜單------------
    public Iterable<MenuEntity> getMenu_all() {
        return menuDao.findAll();
    }
    //------------接值------------
    public Optional<MenuEntity> find_and_order (String item){
        return menuDao.findById(item);
    }
    //------------判斷品項存在並加入------------
    public String find_and_order_exist (Optional<MenuEntity> item){
        if(item.isEmpty()){
            return "不存在";
        }
        else{
            OrderEntity order = new OrderEntity();
            order.setOrder_id(UUID.randomUUID().toString());
            order.setOrder_item(item.get().getItems());
            order.setOrder_price(item.get().getPrice());
            orderDao.save(order);//-----將值寫入table_menu
            Totalprice = Totalprice + item.get().getPrice();//-----加總金額
            return "成功點餐,餐點為:" + item.get().getItems() + ",價格:" + item.get().getPrice();
        }
    }
    //------------加總金額------------
    public Integer totalprice(){
        return Totalprice;
    }
    //--------------------------------------------------------
}
