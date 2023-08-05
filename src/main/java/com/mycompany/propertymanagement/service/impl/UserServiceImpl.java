package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exceptioin.BusinessException;
import com.mycompany.propertymanagement.exceptioin.ErrorModel;

import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserConverter userConverter;
    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        Optional<UserEntity> optEnt = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());

        // Check if user is already registered
        if(optEnt.isPresent()){
            List<ErrorModel> errorModels = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("USER_ALREADY_EXISTS");
            errorModel.setMessage("Account with "+ userDTO.getOwnerEmail() + " already exists...!");
            errorModels.add(errorModel);
            throw new BusinessException(errorModels);
        }else {

            UserEntity ue = userConverter.convertDTOtoEntity(userDTO);

            ue = userRepository.save(ue);

            userDTO = userConverter.convertEntityToDTO(ue);

        }
        return userDTO;
    }

    public boolean loginUser(UserDTO userDTO){
        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
        Optional<UserEntity> opEnt = userRepository.findByOwnerEmailAndPassword(userEntity.getOwnerEmail(), userEntity.getPassword());

        if(opEnt.isPresent()){
            return true;
        }
        else{
            List<ErrorModel> errorModels = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect email or password");
            errorModels.add(errorModel);
            throw new BusinessException(errorModels);
        }
    }
}
