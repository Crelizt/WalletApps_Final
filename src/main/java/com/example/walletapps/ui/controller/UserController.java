package com.example.walletapps.ui.controller;

import com.example.walletapps.service.iservice.IUserInterface;
import com.example.walletapps.shared.dto.UserDTO;
import com.example.walletapps.ui.model.request.UserRequest;
import com.example.walletapps.ui.model.response.UserResponse;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    IUserInterface userService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserResponse> getUsers(){
        List<UserResponse> value = new ArrayList<UserResponse>();
        ModelMapper mapper = new ModelMapper();

        List<UserDTO> users = userService.getListUser();
        for (UserDTO userDTO : users){
            value.add(mapper.map(userDTO, UserResponse.class));
        }
        return value;
    }

    @GetMapping(path = "/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse getUserByUsername(@PathVariable String username){
        UserDTO getUser = userService.getUserByUsername(username);
        if (getUser == null)
            return null;

        ModelMapper mapper = new ModelMapper();
        return mapper.map(getUser, UserResponse.class);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse addNewUser(@RequestBody UserRequest user){
        ModelMapper mapper = new ModelMapper();
        //User Request -> dipindahkan ke UserDTO
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        // Menjalankan function save ke repo
        UserDTO createdUser = userService.addNewData(userDTO);
        //UserDTO -> dipindahkan ke UserResponse
        UserResponse response = mapper.map(createdUser, UserResponse.class);
        return response;
    }


}
