package com.example.walletapps.service.iservice;

import com.example.walletapps.shared.dto.WalletsBalanceDTO;
import com.example.walletapps.shared.dto.WalletsDTO;

import java.util.List;

public interface IWalletsService {
    List<WalletsDTO> getListWallet(String userid);
    WalletsBalanceDTO getTotalBalance(String userid);
    // Alternatif TotalBalance
    long getTotalBalance2(String userid);
    WalletsDTO addNewWalletData(String userid, WalletsDTO walletsDTO);
    WalletsDTO updateWalletData(String userid, String walletid, WalletsDTO walletsDTO);
}
