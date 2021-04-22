package com.example.walletapps.service.iservice;

import com.example.walletapps.shared.dto.TransactionDTO;

import java.util.List;

public interface ITransactionsService {
    List<TransactionDTO> getAllTransactions();

    TransactionDTO addNewTransaction(String walletId, TransactionDTO transactionDTO);

    List<TransactionDTO> getTransactionsByWalletId(String walletId);

    TransactionDTO updateTransacitonByTransactionId(String walletId, String transaction_id, TransactionDTO transactionDTO);

    TransactionDTO deleteTransaction(String walletId, String transactionsId);
}
