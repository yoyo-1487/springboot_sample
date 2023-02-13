package com.example.menu.controller;

import com.example.menu.controller.request.AddInformationToMenuCommand;
import com.example.menu.model.entity.MenuEntity;
import com.example.menu.service.MenuService;
import com.example.menu.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MenuController {
    @Autowired
    MenuService menuService;
    @Autowired
    OrderService orderService;

    //------------拿到菜單------------
    @GetMapping("/getmenu")
    public @ResponseBody List<MenuEntity> findMenuAll () {
        return (List)menuService.getMenuAll();
    }

    //------------得到訂單資料(項目,數量)------------
    @GetMapping("/getorder")
    public @ResponseBody HashMap<String, Integer> getOrderList () {
        return orderService.getOrderItemAndNumber();
    }

    //------------找菜單是否有此品項並點餐------------
    @PostMapping("/find_and_order")
    public @ResponseBody String findAndOrder(@RequestBody AddInformationToMenuCommand command){
        return menuService.findAndOrder(command.getItems());
    }

    //------------得到訂單總金額------------
    @GetMapping("/totalprice")
    public @ResponseBody Integer totalPrice(){
        return menuService.totalPrice();
    }

    //-----------
    @GetMapping("/countprice")
    public @ResponseBody Integer countPrice(){
        return orderService.countPrice();
    }
}
