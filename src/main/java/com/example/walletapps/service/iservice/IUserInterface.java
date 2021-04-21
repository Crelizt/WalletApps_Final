package com.example.walletapps.service.iservice;

import com.example.walletapps.shared.dto.UserDTO;

import java.util.List;

public interface IUserInterface {
    //get all user
    List<UserDTO> getListUser();

    //get single value by username
    UserDTO getUserByUsername(String username);

    //add new username
    UserDTO addNewData(UserDTO user);
}
