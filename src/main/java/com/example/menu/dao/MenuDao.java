package com.example.menu.dao;

import com.example.menu.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 第一個參數為訪問的實體，第二參數是這個Entity ID的資料型態


public interface MenuDao extends JpaRepository<MenuEntity, String> {
}
