package com.example.smartBar.smartBar.repository;

import com.example.smartBar.smartBar.domain.OrderEntity;
import com.example.smartBar.smartBar.domain.OrderItemEntity;
import com.example.smartBar.smartBar.domain.UserEntity;
import com.example.smartBar.smartBar.dto.OrderCreationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByOrderByIdDesc();

    List<OrderEntity> findByUserAndStatus(UserEntity user, String status);
}
