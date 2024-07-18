package com.example.smartBar.smartBar.repository;

import com.example.smartBar.smartBar.domain.OrderEntity;
import com.example.smartBar.smartBar.dto.OrderCreationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
