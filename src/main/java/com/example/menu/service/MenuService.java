package com.example.menu.service;

import com.example.menu.entity.MenuEntity;
import com.example.menu.aggregate.MenuAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    MenuAggregate menuaggregate;
    //------------查詢菜單------------
    public Iterable<MenuEntity> getMenu_all_service() {
        Iterable<MenuEntity> menu_all_service = menuaggregate.getMenu_all();
        return menu_all_service;
    }
    //------------找菜單是否有此品項------------判斷品項存在並加入
    public String find_and_order (String item){
        Optional<MenuEntity> find_and_order = menuaggregate.find_and_order(item);//1.品項,find_and_order=輸入的品項
        return menuaggregate.find_and_order_exist(find_and_order);//2.判斷品項存在並加入
    }
    //------------計算總額------------
    public Integer totalprice(){
        return menuaggregate.totalprice();
    }


}