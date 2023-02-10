package com.example.menu.model.dao;

import com.example.menu.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderEntity, String> {
}
