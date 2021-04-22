package com.example.walletapps.ui.controller;

import com.example.walletapps.io.irepository.WalletsRepository;
import com.example.walletapps.service.iservice.IWalletsService;
import com.example.walletapps.shared.dto.WalletsBalanceDTO;
import com.example.walletapps.shared.dto.WalletsDTO;
import com.example.walletapps.ui.model.request.WalletRequest;
import com.example.walletapps.ui.model.response.WalletBalanceResponse;
import com.example.walletapps.ui.model.response.WalletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/wallets")
public class WalletsController {
    @Autowired
    IWalletsService walletsService;

    @GetMapping(path = "/{userId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<WalletResponse> getAllWallets(@PathVariable String userId){
        List<WalletResponse> value = new ArrayList<WalletResponse>();
        ModelMapper mapper = new ModelMapper();

        List<WalletsDTO> wallets = walletsService.getListWallet(userId);
        for(WalletsDTO walletsDTO: wallets){
            value.add(mapper.map(walletsDTO, WalletResponse.class));
        }

        return value;
    }

    @GetMapping(path = "/{userId}/balance", produces = {MediaType.APPLICATION_JSON_VALUE})
    public WalletBalanceResponse getWalletBalance(@PathVariable String userId){
        ModelMapper mapper = new ModelMapper();

        WalletsBalanceDTO value = walletsService.getTotalBalance(userId);

        return mapper.map(value, WalletBalanceResponse.class);
    }

    // Alternatif getTotalBalance
    @GetMapping(path = "/{userId}/balance2", produces = {MediaType.APPLICATION_JSON_VALUE})
    public long getTotalBalance2(@PathVariable String userId){
        long totalBalance = walletsService.getTotalBalance2(userId);
        return totalBalance;
    }

    @PostMapping(path = "/{userId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public WalletResponse addNewWallet(@PathVariable String userId,@RequestBody WalletRequest walletRequest){
        ModelMapper mapper = new ModelMapper();

        WalletsDTO walletsDTO = mapper.map(walletRequest, WalletsDTO.class);
        WalletsDTO createdWallet = walletsService.addNewWalletData(userId, walletsDTO);

        return mapper.map(createdWallet, WalletResponse.class);
    }

    @PutMapping(path = "/{userId}/{walletId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public WalletResponse updateWalletAmount(@PathVariable String userId, @PathVariable String walletId,
                                             @RequestBody WalletRequest walletRequest){
        ModelMapper mapper = new ModelMapper();
        WalletsDTO walletsDTO = mapper.map(walletRequest, WalletsDTO.class);

        WalletsDTO updatedData = walletsService.updateWalletData(userId, walletId, walletsDTO);

        return mapper.map(updatedData, WalletResponse.class);
    }
}
