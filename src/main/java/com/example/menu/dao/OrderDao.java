package com.example.menu.dao;

import com.example.menu.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderEntity, String> {
}
