package com.mycompany.propertymanagement.converter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity convertDTOtoEntity(UserDTO userDTO){
        UserEntity ue = new UserEntity();
        ue.setOwnerEmail(userDTO.getOwnerEmail());
        ue.setFirstName(userDTO.getFirstName());
        ue.setLastName(userDTO.getLastName());
        ue.setPassword(userDTO.getPassword());

        return ue;
    }
    public UserDTO convertEntityToDTO( UserEntity userEntity){

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setOwnerEmail(userEntity.getOwnerEmail());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());


        return userDTO;
    }
}
