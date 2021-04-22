package com.example.walletapps.io.irepository;

import com.example.walletapps.io.entity.TransactionEntity;
import com.example.walletapps.io.entity.WalletsEntity;
import com.example.walletapps.shared.dto.WalletsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findAllByWalletsEntity(WalletsEntity walletsEntity);
    TransactionEntity findByTransactionId(String transactionId);
}
