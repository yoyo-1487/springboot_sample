package com.example.menu.service;

import com.example.menu.dao.MenuDao;
import com.example.menu.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    MenuDao menuDao;//取得Dao物件

    public Iterable<MenuEntity> getMenu() {//查詢菜單

        return menuDao.findAll();
    }
    public void add(MenuEntity addmenu) {//點餐
        menuDao.save(addmenu);
    }
    public Optional<MenuEntity> findMenu (String name){
        Optional<MenuEntity> todo = menuDao.findById(name);
        return todo;
    }


}