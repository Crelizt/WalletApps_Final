package com.example.walletapps.io.irepository;

import com.example.walletapps.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByUserid(String userid);
}

