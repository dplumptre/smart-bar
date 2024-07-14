package com.example.smartBar.smartBar.repository;

import com.example.smartBar.smartBar.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByPhoneNumber(String phoneNumber);
}
