package com.example.walletapps.service.impl;

import com.example.walletapps.io.entity.UserEntity;
import com.example.walletapps.io.irepository.UserRepository;
import com.example.walletapps.service.iservice.IUserInterface;
import com.example.walletapps.shared.dto.UserDTO;
import com.example.walletapps.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserInterface {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GenerateRandomPublicId generateRandomPublicId;

    @Override
    public List<UserDTO> getListUser() {
        List<UserDTO> value = new ArrayList<UserDTO>();
        ModelMapper mapper = new ModelMapper();
        // Get all user from database
        List<UserEntity> users = userRepository.findAll();

        //List<UserEntity> -> mapping ke List<UserDTO>
        for(UserEntity userEntity : users){
            value.add(mapper.map(userEntity, UserDTO.class));
        }
        return value;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        UserEntity getUser = userRepository.findByUsername(username);
        if (getUser == null)
            return null;

        ModelMapper mapper = new ModelMapper();
        return mapper.map(getUser, UserDTO.class);
    }

    @Override
    public UserDTO addNewData(UserDTO user) {
        user.setUserId(generateRandomPublicId.generateUserId(30)); // generate user id

        ModelMapper mapper = new ModelMapper();
        //UserDTO -> di pindahkan ke UserEntity
        UserEntity entity = mapper.map(user, UserEntity.class);

        //UserEntity akan save di repository dan disave ke storedData
        UserEntity storedData = userRepository.save(entity);

        //UserEntity -> dipindahkan lagi ke UserDTO
        UserDTO value = mapper.map(storedData, UserDTO.class);
        return value;
    }
}
