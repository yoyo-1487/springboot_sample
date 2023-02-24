package com.example.menu.model.dao;

import com.example.menu.model.entity.AdminAccountEntity;
import com.example.menu.model.entity.OrderEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminAccountDao extends JpaRepository<AdminAccountEntity, Long> {

  @Query("SELECT o FROM AdminAccountEntity o WHERE o.adminName = :adminName")
  Optional<AdminAccountEntity> findByAdminName(@Param("adminName") String adminName);
}
