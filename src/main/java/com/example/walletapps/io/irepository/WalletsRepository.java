package com.example.walletapps.io.irepository;

import com.example.walletapps.io.entity.UserEntity;
import com.example.walletapps.io.entity.WalletsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletsRepository extends JpaRepository<WalletsEntity, Long> {
    List<WalletsEntity> findAllByUser(UserEntity userEntity);
    WalletsEntity findByWalletid(String walletid);
}
