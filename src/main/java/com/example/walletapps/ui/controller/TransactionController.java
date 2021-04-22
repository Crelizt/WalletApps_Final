package com.example.walletapps.ui.controller;

import com.example.walletapps.service.iservice.ITransactionsService;
import com.example.walletapps.shared.dto.TransactionDTO;
import com.example.walletapps.ui.model.request.TransactionRequest;
import com.example.walletapps.ui.model.response.TransactionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {
    @Autowired
    ITransactionsService iTransactionsService;

    //Autowired dapat diganti:
//    private final ITransactionsService iTransactionsService;
//
//    public TransactionController(ITransactionsService iTransactionsService){
//        this.iTransactionsService = iTransactionsService;
//    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TransactionResponse> getAllTransactions(){
        ModelMapper mapper = new ModelMapper();
        List<TransactionResponse> returnValue = new ArrayList<>();

        List<TransactionDTO> transactions = iTransactionsService.getAllTransactions();
        for (TransactionDTO transactionDTO: transactions){
            returnValue.add(mapper.map(transactionDTO, TransactionResponse.class));
        }
        return returnValue;
    }

    @GetMapping(path = "/{walletId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TransactionResponse> getTransactionsByWalletId(@PathVariable String walletId){
        ModelMapper mapper = new ModelMapper();
        List<TransactionResponse> returnValue = new ArrayList<>();

        List<TransactionDTO> transactions = iTransactionsService.getTransactionsByWalletId(walletId);

        for (TransactionDTO transactionDTO: transactions){
            returnValue.add(mapper.map(transactionDTO, TransactionResponse.class));
        }
        return returnValue;
    }

    @PostMapping(path = "/{walletId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public TransactionResponse addNewTransactions(@PathVariable String walletId,
                                                  @RequestBody TransactionRequest transactionRequest){
        ModelMapper mapper = new ModelMapper();
        TransactionResponse returnValue = new TransactionResponse();

        TransactionDTO transactionDTO = mapper.map(transactionRequest, TransactionDTO.class);
        TransactionDTO storedValue = iTransactionsService.addNewTransaction(walletId, transactionDTO);

        return mapper.map(storedValue, TransactionResponse.class);
    }

    @PutMapping(path = "/{walletId}/{transactionsId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public TransactionResponse updateTransaction(@PathVariable String walletId, @PathVariable String transactionsId,
                                                 @RequestBody TransactionRequest transactionRequest){
        ModelMapper mapper = new ModelMapper();

        TransactionDTO transactionDTO = mapper.map(transactionRequest, TransactionDTO.class);

        TransactionDTO updatedData = iTransactionsService.updateTransacitonByTransactionId(walletId, transactionsId, transactionDTO);

        return mapper.map(updatedData, TransactionResponse.class);
    }

    @DeleteMapping(path = "/{walletId}/{transactionsId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public TransactionResponse deleteTransaction(@PathVariable String walletId, @PathVariable String transactionsId){
        ModelMapper mapper = new ModelMapper();

        TransactionDTO transactionDTO = iTransactionsService.deleteTransaction(walletId, transactionsId);

        return mapper.map(transactionDTO, TransactionResponse.class);
    }
}
