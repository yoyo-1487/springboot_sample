package com.example.menu.controller;

import com.example.menu.controller.request.AddInformationToMenuCommand;
import com.example.menu.service.MenuService;
import com.example.menu.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MenuController {

  @Autowired
  MenuService menuService;
  @Autowired
  OrderService orderService;

  //------------拿到菜單------------
  @PostMapping("/getmenu")
  public String findMenuAll(Model model) {//List<MenuEntity>
    model.addAttribute("findMenuAll", menuService.getMenuAll());
    return "menu/ShowMenu";
    //return (List) menuService.getMenuAll();
  }

  //------------得到訂單資料(項目,數量)------------
  @PostMapping("/getorder")
  public String getOrderList(Model model) {//HashMap<String, Integer>
    model.addAttribute("getOrderList", orderService.getOrderItemAndNumber());//訂單名稱跟數量
    model.addAttribute("totalprice", orderService.countPrice());//訂單金額
    return "menu/ShowOrders";
    //return orderService.getOrderItemAndNumber();
  }

  //------------找菜單是否有此品項並點餐------------
  @PostMapping("/find")
  public String form(Model model) {
    AddInformationToMenuCommand addInformationToMenu = new AddInformationToMenuCommand();//實體化
    model.addAttribute("addInformationToMenu", addInformationToMenu);
    return "menu/AddInformationToMenu"; // 導至find.html
  }

  @PostMapping("/andOrder")
  public String findAndOrder(
      @ModelAttribute AddInformationToMenuCommand addInformationToMenu,
      Model model) {//@RequestBody AddInformationToMenuCommand command
    model.addAttribute("addInformationToMenu",
        menuService.findAndOrder((addInformationToMenu.getItems())));
    return "menu/AndOrder";
    //return menuService.findAndOrder(command.getItems());
  }

  //-----------得到訂單總金額------------
  @PostMapping("/countprice")
  public String countPrice(Model model) {//Integer
    model.addAttribute("totalprice", orderService.countPrice());
    return "menu/ShowPrice";
    //return orderService.countPrice();
  }
}
