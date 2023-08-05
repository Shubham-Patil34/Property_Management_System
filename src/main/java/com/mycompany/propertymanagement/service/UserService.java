package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.UserDTO;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    boolean loginUser(UserDTO userDTO);

}
