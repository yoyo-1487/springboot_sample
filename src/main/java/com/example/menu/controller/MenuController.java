package com.example.menu.controller;

import com.example.menu.controller.request.AddInformationToMenuCommand;
import com.example.menu.controller.request.DelOrderCommand;
import com.example.menu.model.dao.MenuDao;
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
  @Autowired
  MenuDao menuDao;


  //------------拿到菜單------------
  @GetMapping("/getMenu")
  public String findMenuAll(Model model,
      @ModelAttribute AccountEntity showInfoOnGetMenu) {//List<MenuEntity>
    model.addAttribute("showAccountPassword", showInfoOnGetMenu);
    model.addAttribute("findMenuAll", menuService.getMenuAll());
    return "menu/ShowMenu";
    //return (List) menuService.getMenuAll();
  }

  //------------得到訂單資料(項目,數量)------------
  @GetMapping("/getOrder")
  public String getOrderList(Model model,
      @ModelAttribute AccountEntity showInfoOnGetMenu) {//HashMap<String, Integer>
    String username = showInfoOnGetMenu.getUsername();
    model.addAttribute("showAccountPassword", showInfoOnGetMenu);
    model.addAttribute("getOrderList", orderService.getOrderItemAndNumber(username));//訂單名稱跟數量
    model.addAttribute("totalPrice", orderService.countPrice(username));//訂單金額
    return "menu/ShowOrders";
    //return orderService.getOrderItemAndNumber();
  }

  //------------找菜單是否有此品項並點餐------------
  @GetMapping("/find")
  public String find(Model model, @ModelAttribute AccountEntity showInfoOnGetMenu,
      HttpServletRequest request) {
    model.addAttribute("addInformationToMenu", orderService.setUser(showInfoOnGetMenu.getUsername(),
        request.getSession()));//使用者已經設定好，餐點至點餐畫面設定
    model.addAttribute("menuItems", menuService.getMenuAll());//從資料庫撈菜單顯示在點餐欄
    return "menu/Find"; // 導至find.html
  }

  @GetMapping("/andOrder")
  public String andOrder(@ModelAttribute AddInformationToMenuCommand find,
      Model model) {//@RequestBody AddInformationToMenuCommand command
    model.addAttribute("find", menuService.findAndOrder(find.getItems(), find.getUsername()));
    return "menu/AndOrder";
    //return menuService.findAndOrder(command.getItems());
  }

  //------------del
  @GetMapping("/chooseDel")
  public String chooseOrder(Model model, @ModelAttribute AccountEntity showInfoOnDelOrder,
      HttpServletRequest request) {
    HttpSession session = request.getSession();
    AccountEntity account=new AccountEntity();
    if(showInfoOnDelOrder.getUsername()==null){
      account.setUsername(session.getAttribute("username").toString());
    }else{
      session.setAttribute("username",showInfoOnDelOrder.getUsername());
      account.setUsername(showInfoOnDelOrder.getUsername());
    }
    model.addAttribute("addInformationToDel", orderService.setUser(account.getUsername(),
        request.getSession()));//使用者已經設定好，刪除餐點至畫面設定
    model.addAttribute("OrderItems",
        orderService.chooseOrder(account.getUsername()));//從資料庫撈已點項目顯示在點餐欄
    return "menu/ChooseDel";
  }

  @GetMapping("/andDel")
  public String andDel(@ModelAttribute AddInformationToMenuCommand nameAndItem,
      Model model) {
    orderService.selectIdByUsernameAndItem(nameAndItem.getUsername(), nameAndItem.getItems());
    return "menu/DelSuc";
  }

}
