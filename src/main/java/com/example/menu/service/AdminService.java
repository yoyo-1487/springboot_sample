package com.example.menu.service;

import com.example.menu.model.dao.AdminAccountDao;
import com.example.menu.model.dao.MenuDao;
import com.example.menu.model.entity.AdminAccountEntity;
import com.example.menu.model.entity.MenuEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  @Autowired
  MenuDao menuDao;

  @Autowired
  AdminAccountDao adminAccountDao;

  public String AdminLogin(String adminName, String adminPwd) {
    Optional<AdminAccountEntity> adminAccountOptional = adminAccountDao.findByAdminName(adminName);
    if (adminAccountOptional.isEmpty()) {
      return "Admin/AdminLoginError";
    } else {
      if (!adminPwd.equals(adminAccountOptional.get().getAdminPwd())) {
        return "Admin/AdminLoginError";
      } else {
        return "Admin/AdminLoginSuc";
      }
    }
  }

  public List<MenuEntity> getMenu() {
    return menuDao.findAll();
  }

  public void adminEditMenu(String item, Integer price) {
    Optional<MenuEntity> menuEntityOptional = menuDao.findById(item);
    MenuEntity menuEntity = menuEntityOptional.get();
    menuEntity.setPrice(price);
    menuDao.save(menuEntity);
  }

  public void adminDelMenu(String item) {
    menuDao.deleteById(item);
  }

  public void adminAddMenu(String item, Integer price) {
    MenuEntity menuEntity = new MenuEntity();
    menuEntity.setItems(item);
    menuEntity.setPrice(price);
    menuDao.save(menuEntity);
  }

}
