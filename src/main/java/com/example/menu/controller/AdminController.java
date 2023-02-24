package com.example.menu.controller;

import com.example.menu.controller.request.admin.AddItemPriceIntoMenu;
import com.example.menu.controller.request.admin.AdminLoginCommand;
import com.example.menu.controller.request.admin.DelMenuItem;
import com.example.menu.controller.request.admin.EditMenuItemPrice;
import com.example.menu.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AdminController {

  @Autowired
  AdminService adminService;

  //登入
  @GetMapping("/AdminLogin")
  public String adminLogin(Model model) {
    model.addAttribute("iptAdminAccPwd", new AdminLoginCommand());
    return "Admin/AdminLogin";
  }

  @GetMapping("/AdminLoginSuc")
  public String adminLoginSuc(@ModelAttribute AdminLoginCommand loginCommand,
      HttpServletRequest request) {
    HttpSession session = request.getSession();
    AdminLoginCommand adminLoginCommand = new AdminLoginCommand();
    if (loginCommand.getAdminName() == null || loginCommand.getAdminPwd() == null) {
      adminLoginCommand.setAdminName((String) session.getAttribute("adminName"));
      adminLoginCommand.setAdminPwd((String) session.getAttribute("adminPwd"));
      return adminService.AdminLogin(adminLoginCommand.getAdminName(),
          adminLoginCommand.getAdminPwd());
    } else {
      session.setAttribute("adminName", loginCommand.getAdminName());
      session.setAttribute("adminPwd", loginCommand.getAdminPwd());
      return adminService.AdminLogin(loginCommand.getAdminName(), loginCommand.getAdminPwd());
    }

  }

  //新增
  @GetMapping("/adminAddMenu")
  public String adminAddMenu(Model model) {//輸入要更改的品項與價格item,price
    model.addAttribute("addMenu", new AddItemPriceIntoMenu());//接收更改菜單的欄位
    return "Admin/adminAddMenu";
  }

  @GetMapping("/adminAddMenuSuc")
  public String adminAddMenuSuc(
      @ModelAttribute AddItemPriceIntoMenu addItemPriceIntoMenu) {//輸入要更改的品項與價格item,price
    adminService.adminAddMenu(addItemPriceIntoMenu.getItem(), addItemPriceIntoMenu.getPrice());
    return "Admin/adminAddMenuSuc";
  }

  //修改
  @GetMapping("/AdminEditMenu")
  public String adminEditMenu(Model model) {//輸入要更改的品項與價格item,price
    model.addAttribute("editMenu", new EditMenuItemPrice());//接收更改菜單的欄位
    model.addAttribute("menu", adminService.getMenu());//菜單
    return "Admin/AdminEditMenu";
  }

  @GetMapping("/AdminEditMenuSuc")
  public String adminEditMenuSuc(
      @ModelAttribute EditMenuItemPrice editMenuItemPrice, Model model) {//輸入要更改的品項與價格item,price
    adminService.adminEditMenu(editMenuItemPrice.getItem(), editMenuItemPrice.getPrice());
    model.addAttribute("newPrice", editMenuItemPrice);
    return "Admin/AdminEditMenuSuc";
  }

  //刪除
  @GetMapping("/AdminDelMenu")
  public String adminDelMenu(Model model) {//輸入要更改的品項與價格item,price
    model.addAttribute("delMenu", new DelMenuItem());//接收刪除菜單的欄位
    model.addAttribute("menu", adminService.getMenu());//菜單
    return "Admin/AdminDelMenu";
  }

  @GetMapping("/AdminDelMenuSuc")
  public String adminDelMenuSuc(
      @ModelAttribute DelMenuItem delMenuItem) {//輸入要更改的品項與價格item,price
    adminService.adminDelMenu(delMenuItem.getItem());
    return "Admin/AdminDelMenuSuc";
  }
}
