package com.example.walletapps.service.impl;

import com.example.walletapps.io.entity.UserEntity;
import com.example.walletapps.io.entity.WalletsEntity;
import com.example.walletapps.io.irepository.UserRepository;
import com.example.walletapps.io.irepository.WalletsRepository;
import com.example.walletapps.service.iservice.IWalletsService;
import com.example.walletapps.shared.dto.UserDTO;
import com.example.walletapps.shared.dto.WalletsBalanceDTO;
import com.example.walletapps.shared.dto.WalletsDTO;
import com.example.walletapps.shared.utils.GenerateRandomPublicId;
import com.example.walletapps.ui.model.response.WalletBalanceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletsServiceImpl implements IWalletsService {
    @Autowired
    WalletsRepository walletsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GenerateRandomPublicId generateRandomPublicId;

    @Override
    public List<WalletsDTO> getListWallet(String userid) {
        ModelMapper mapper = new ModelMapper();

        List<WalletsDTO> value = new ArrayList<WalletsDTO>();

        UserEntity userEntity = userRepository.findByUserid(userid);

        List<WalletsEntity> wallets = walletsRepository.findAllByUser(userEntity);

        for (WalletsEntity walletsEntity: wallets){
            value.add(mapper.map(walletsEntity, WalletsDTO.class));
        }

        return value;

        //proses memapping list WalletsEntity jadi WalletsDTO dapat dipersingkat dengan:
        //return new ModelMapper.map(wallets, new TypeToken<List<WalletsDTO>>(){}.getType());
    }

    @Override
    public WalletsBalanceDTO getTotalBalance(String userid) {
        ModelMapper mapper = new ModelMapper();

        UserEntity userData = userRepository.findByUserid(userid);

        List<WalletsEntity> wallets = walletsRepository.findAllByUser(userData);

        if (wallets == null)
            return null;

        long totalBalance = 0;
        for (WalletsEntity walletsEntity: wallets){
            totalBalance = totalBalance + walletsEntity.getBalance();
        }

        WalletsBalanceDTO value = new WalletsBalanceDTO();
        value.setTotalBalance(totalBalance);
        value.setUserName(userData.getUsername());

        return value;
    }

    //Alternatif get total balance
    @Override
    public long getTotalBalance2(String userid) {
        List<WalletsEntity> walletsData = walletsRepository.findAllByUser(userRepository.findByUserid(userid));
        if (walletsData == null)
            return 0L;

        long totalBalance = 0;
        for(WalletsEntity walletsEntity: walletsData){
            totalBalance = totalBalance + walletsEntity.getBalance();
        }
        return totalBalance;
    }

    @Override
    public WalletsDTO addNewWalletData(String userid, WalletsDTO walletsDTO) {
        ModelMapper mapper = new ModelMapper();

        //Generate Wallets Id
        walletsDTO.setWalletId(generateRandomPublicId.generateUserId(30));
        //Get User
        UserEntity userData = userRepository.findByUserid(userid);
        //Set User
        walletsDTO.setUser(mapper.map(userData, UserDTO.class));

        WalletsEntity entity = mapper.map(walletsDTO, WalletsEntity.class);

        //Save data ke Database (table: walletstbl)
        WalletsEntity storedData = walletsRepository.save(entity);

        return mapper.map(storedData, WalletsDTO.class);
    }

    @Override
    public WalletsDTO updateWalletData(String userid, String walletid, WalletsDTO walletsDTO) {
        WalletsEntity walletData = walletsRepository.findByWalletid(walletid);
        if(walletData == null || !userid.equals(walletData.getUser().getUserid()))
            return null;

        // update noHP (or) Balance
        walletData.setNoHP(walletsDTO.getNoHP());
        walletData.setBalance(walletsDTO.getBalance());

        WalletsEntity updatedData = walletsRepository.save(walletData);

        return new ModelMapper().map(updatedData, WalletsDTO.class);
    }
}
