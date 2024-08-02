package com.example.smartBar.smartBar.repository;

import com.example.smartBar.smartBar.domain.PaymentMethodEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity, Long > {
}
