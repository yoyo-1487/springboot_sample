package com.example.menu.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.menu.model.entity.AccountEntity;
public interface LoginDao extends JpaRepository<AccountEntity, String> {

}
