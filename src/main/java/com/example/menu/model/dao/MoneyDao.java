package com.example.menu.model.dao;

import com.example.menu.model.entity.MoneyEntity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface MoneyDao extends JpaRepository<MoneyEntity, Integer> {

}
