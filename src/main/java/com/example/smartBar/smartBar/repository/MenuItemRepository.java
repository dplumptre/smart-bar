package com.example.smartBar.smartBar.repository;

import com.example.smartBar.smartBar.domain.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long> {
}
