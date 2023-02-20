package com.example.menu.model.dao;

import com.example.menu.model.entity.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDao extends JpaRepository<OrderEntity, String> {

  @Query("SELECT o FROM OrderEntity o WHERE o.orderUsername = :username")
  List<OrderEntity> findByUsername(@Param("username") String username);
}
