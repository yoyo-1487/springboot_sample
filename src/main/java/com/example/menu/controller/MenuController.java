package com.example.menu.controller;

import com.example.menu.controller.request.user.AddInformationToMenuCommand;
import com.example.menu.controller.request.user.InsertAccountPasswordCommand;
import com.example.menu.model.dao.LoginDao;
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
  LoginDao loginDao;


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

  //------------刪除功能,由username找出訂單在撈出來顯示------------
  @GetMapping("/chooseDel")
  public String chooseOrder(Model model, @ModelAttribute AccountEntity showInfoOnDelOrder,
      HttpServletRequest request) {//showInfoOnDelOrder=使用者帳號
    HttpSession session = request.getSession();
    AccountEntity account = new AccountEntity();
    if (showInfoOnDelOrder.getUsername() == null) {//回到頁面時showInfoOnDelOrder會是空,所以要存起來
      account.setUsername(session.getAttribute("username").toString());
    } else {
      session.setAttribute("username", showInfoOnDelOrder.getUsername());
      account.setUsername(showInfoOnDelOrder.getUsername());
    }
    model.addAttribute("addInformationToDel", orderService.setUser(account.getUsername(),
        request.getSession()));//使用者已經設定好，刪除餐點至畫面設定
    if (!orderService.chooseOrder(account.getUsername()).isEmpty()) {
      model.addAttribute("OrderItems",
          orderService.chooseOrder(account.getUsername()));//從資料庫撈已點項目顯示在點餐欄
      return "menu/ChooseDel";
    } else {
      return "menu/OrderIsEmpty";
    }

  }

  @GetMapping("/andDel")
  public String andDel(@ModelAttribute AddInformationToMenuCommand nameAndItem) {
    orderService.selectIdByUsernameAndItem(nameAndItem.getUsername(), nameAndItem.getItems());
    return "menu/DelSuc";
  }

  //-------------------------------------
  @GetMapping("/changePwd")
  public String changePwd(@ModelAttribute AddInformationToMenuCommand name, Model model) {
    InsertAccountPasswordCommand insertAccountPasswordCommand = new InsertAccountPasswordCommand();
    insertAccountPasswordCommand.setAccount(name.getUsername());
    model.addAttribute("setNewPwd", insertAccountPasswordCommand);
    return "ChangePwd/ChangePwd";
  }

  @GetMapping("/changePwdCk")
  public String changePwdCk(@ModelAttribute InsertAccountPasswordCommand check, Model model) {
    AccountEntity account = loginDao.findById(check.getAccount()).get();//由user找到帳號資料
    if (check.getPassword().isEmpty() || check.getPasswordCheck().isEmpty()) {
      //error密碼與確認密碼為空
      return "ChangePwd/ChangePwdEmp";
    } else {
      if (!(check.getPassword().equals(account.getPassword()))) {
        //error原密碼輸入錯誤
        return "ChangePwd/ChangePwdErr";
      } else {
        if (check.getPasswordCheck().equals(account.getPassword())) {
          //error新密碼與原密碼相同
          return "ChangePwd/ChangePwdSame";
        } else {
          account.setPassword(check.getPasswordCheck());//更新成新的password
          loginDao.save(account);//存到資料庫
          check.setPassword(check.getPasswordCheck());//將傳遞回home的密碼設定成新密碼
          model.addAttribute("sentToHome", check);
          return "ChangePwd/ChangePwdSuc";
        }

      }
    }

  }
}
