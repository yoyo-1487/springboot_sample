package com.example.menu.controller;

import com.example.menu.controller.request.AddInformationToMenuCommand;
import com.example.menu.entity.MenuEntity;
import com.example.menu.entity.SetItemAmountEntity;
import com.example.menu.service.MenuService;
import com.example.menu.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MenuController {
    @Autowired
    MenuService menuService;
    @Autowired
    OrderService orderService;
    public int totalprice=0;
    //------------拿到菜單------------ok
    @GetMapping("/getmenu")
    public Iterable<MenuEntity> findMenu_all () {
        return menuService.getMenu_all_service();
    }
    //------------
    @GetMapping("/getorder")
    public List<SetItemAmountEntity> getOrderList () {
        return orderService.getOrder_all();
    }
    //------------找菜單是否有此品項並點餐------------ok
    @PostMapping("/find_and_order")
    public String find_and_order(@RequestBody AddInformationToMenuCommand command){
        return menuService.find_and_order(command.getItems());
    }
    //------------
    @GetMapping("/totalprice")
    public Integer totalprice(){
        return menuService.totalprice();
    }
}
