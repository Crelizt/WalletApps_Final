package com.example.walletapps.service.iservice;

import com.example.walletapps.shared.dto.UserDTO;

import java.util.List;

public interface IUserInterface {
    List<UserDTO> getListUser();

    UserDTO addNewData(UserDTO user);
}
