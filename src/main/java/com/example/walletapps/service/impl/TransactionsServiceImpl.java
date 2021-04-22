package com.example.walletapps.service.impl;

import com.example.walletapps.io.entity.TransactionEntity;
import com.example.walletapps.io.entity.WalletsEntity;
import com.example.walletapps.io.irepository.TransactionRepository;
import com.example.walletapps.io.irepository.WalletsRepository;
import com.example.walletapps.service.iservice.ITransactionsService;
import com.example.walletapps.shared.dto.TransactionDTO;
import com.example.walletapps.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsServiceImpl implements ITransactionsService {
    private final TransactionRepository transactionRepository;
    private final WalletsRepository walletsRepository;
    private final GenerateRandomPublicId generateRandomPublicId;

    public TransactionsServiceImpl(TransactionRepository transactionRepository, WalletsRepository walletsRepository, GenerateRandomPublicId generateRandomPublicId) {
        this.transactionRepository = transactionRepository;
        this.walletsRepository = walletsRepository;
        this.generateRandomPublicId = generateRandomPublicId;
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        ModelMapper mapper = new ModelMapper();
        List<TransactionDTO> value = new ArrayList<TransactionDTO>();

        List<TransactionEntity> transactions = transactionRepository.findAll();

        for (TransactionEntity transactionEntity: transactions){
            value.add(mapper.map(transactionEntity, TransactionDTO.class));
        }

        return value;
    }

    @Override
    public List<TransactionDTO> getTransactionsByWalletId(String walletId) {
        ModelMapper mapper = new ModelMapper();
        List<TransactionDTO> value = new ArrayList<>();

        List<TransactionEntity> transactions = transactionRepository.findAllByWalletsEntity(walletsRepository.findByWalletid(walletId));

        for (TransactionEntity transactionEntity: transactions){
            value.add(mapper.map(transactionEntity, TransactionDTO.class));
        }
        return value;
    }

    @Override
    public TransactionDTO addNewTransaction(String walletId, TransactionDTO transactionDTO) {
        ModelMapper mapper = new ModelMapper();

        WalletsEntity walletsEntity = walletsRepository.findByWalletid(walletId);

        TransactionEntity entity = mapper.map(transactionDTO, TransactionEntity.class);
        entity.setWalletsEntity(walletsEntity);
        entity.setTransaction_id(generateRandomPublicId.generateUserId(35));

        TransactionEntity storedValue = transactionRepository.save(entity);

        TransactionDTO returnValue = mapper.map(storedValue, TransactionDTO.class);
        return returnValue;
    }

    @Override
    public TransactionDTO updateTransacitonByTransactionId(String walletId, String transactionId, TransactionDTO transactionDTO) {

        long balanceInit = 0;
        long amountInit = 0;
        long amountUpdate = transactionDTO.getAmount();


        ModelMapper mapper = new ModelMapper();
        WalletsEntity walletsEntity = walletsRepository.findByWalletid(walletId);
        balanceInit = walletsEntity.getBalance();

        TransactionEntity transactionEntity = transactionRepository.findByTransactionId(transactionId);
        amountInit = transactionEntity.getAmount();

        if(amountInit > amountUpdate){
            walletsEntity.setBalance(balanceInit + (amountInit - amountUpdate));
        }else {
            walletsEntity.setBalance(balanceInit - (amountUpdate - amountInit));
        }

        TransactionEntity entity = mapper.map(transactionDTO, TransactionEntity.class);
        entity.setWalletsEntity(walletsEntity);
        entity.setId(transactionEntity.getId());
        entity.setTransaction_id(transactionEntity.getTransaction_id());

        TransactionEntity value = transactionRepository.save(entity);
        return mapper.map(value, TransactionDTO.class);
    }

    @Override
    public TransactionDTO deleteTransaction(String walletId, String transactionsId) {
        WalletsEntity walletsEntity = walletsRepository.findByWalletid(walletId);
        TransactionEntity transactionEntity = transactionRepository.findByTransactionId(transactionsId);

        transactionEntity.setWalletsEntity(walletsEntity);
        transactionEntity.setDeleted(true);

        TransactionEntity storedData = transactionRepository.save(transactionEntity);

        ModelMapper mapper = new ModelMapper();
        return mapper.map(storedData, TransactionDTO.class);
    }
}
