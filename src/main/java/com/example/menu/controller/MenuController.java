package com.example.menu.controller;

import com.example.menu.controller.request.AddInformationToMenuCommand;
import com.example.menu.model.entity.AccountEntity;
import com.example.menu.service.MenuService;
import com.example.menu.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
  @GetMapping("/getmenu")
  public String findMenuAll(Model model,
      @ModelAttribute AccountEntity showInfoOnGetmenu) {//List<MenuEntity>
    model.addAttribute("showAccountPassword", showInfoOnGetmenu);
    model.addAttribute("findMenuAll", menuService.getMenuAll());
    return "menu/ShowMenu";
    //return (List) menuService.getMenuAll();
  }

  //------------得到訂單資料(項目,數量)------------
  @GetMapping("/getorder")
  public String getOrderList(Model model,
      @ModelAttribute AccountEntity showInfoOnGetmenu) {//HashMap<String, Integer>
    String username = showInfoOnGetmenu.getUsername();
    model.addAttribute("showAccountPassword", showInfoOnGetmenu);
    model.addAttribute("getOrderList", orderService.getOrderItemAndNumber(username));//訂單名稱跟數量
    model.addAttribute("totalprice", orderService.countPrice(username));//訂單金額
    return "menu/ShowOrders";
    //return orderService.getOrderItemAndNumber();
  }

  //------------找菜單是否有此品項並點餐------------
  @GetMapping("/find")
  public String form(Model model,
      @ModelAttribute AccountEntity showInfoOnGetmenu,
      HttpServletRequest request) {

    AccountEntity accountEntity = new AccountEntity();//登入者資訊

    AddInformationToMenuCommand addInformationToMenuCommand = new AddInformationToMenuCommand();

    HttpSession session = request.getSession();
    String username = showInfoOnGetmenu.getUsername();
    //-----------判斷傳入值是否為空----------
    if (username != null) {//如果不為空
      accountEntity.setUsername(username);
      session.setAttribute("name", username);//存到session
      addInformationToMenuCommand.setUsername(username);//設定哪位使用者點餐
    } else {//如果為空
      //從session撈資料
      accountEntity.setUsername(session.getAttribute("name").toString());
      addInformationToMenuCommand.setUsername(session.getAttribute("name").toString());//設定哪位使用者點餐
    }

    model.addAttribute("showAccountPassword", accountEntity);
    model.addAttribute("addInformationToMenu", addInformationToMenuCommand);//使用者已經設定好，餐點至點餐畫面設定
    return "menu/Find"; // 導至find.html
  }

  @GetMapping("/andOrder")
  public String findAndOrder(
      @ModelAttribute AddInformationToMenuCommand find,
      Model model) {//@RequestBody AddInformationToMenuCommand command
    model.addAttribute("find",
        menuService.findAndOrder(find.getItems(),
            find.getUsername()));
    return "menu/AndOrder";
    //return menuService.findAndOrder(command.getItems());
  }

  //-----------得到訂單總金額------------
//  @GetMapping("/countprice")
//  public String countPrice(Model model) {//Integer
//    model.addAttribute("totalprice", orderService.countPrice());
//    return "menu/ShowPrice";
//    //return orderService.countPrice();
//  }
}
