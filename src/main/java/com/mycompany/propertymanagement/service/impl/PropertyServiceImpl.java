package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exceptioin.BusinessException;
import com.mycompany.propertymanagement.exceptioin.ErrorModel;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    PropertyConverter propertyConverter;

    @Autowired
    UserRepository userRepository;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) { // factory design pattern

        Optional<UserEntity> optEnt = userRepository.findById(propertyDTO.getUserId());

        if (optEnt.isPresent()){
            PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
            pe.setUserEntity(optEnt.get());
            pe = propertyRepository.save(pe);
            propertyDTO = propertyConverter.convertEntityToDTO(pe);


            return propertyDTO;
        }
        List<ErrorModel> errorModels = new ArrayList<>();
        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode("USER_DOES_NOT_EXISTS");
        errorModel.setMessage("Not able to find the user, please do registration first.");
        errorModels.add(errorModel);
        throw new BusinessException(errorModels);


    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> listOfPropertyEntity = (List<PropertyEntity>)propertyRepository.findAll();
        List<PropertyDTO> listOfPropertyDTO = new ArrayList<>();

        for (PropertyEntity propEntity: listOfPropertyEntity){
            PropertyDTO propertyDTO = propertyConverter.convertEntityToDTO(propEntity);
            listOfPropertyDTO.add(propertyDTO);
        }
        return listOfPropertyDTO;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity>  optEnt = propertyRepository.findById(propertyId);

        if(optEnt.isPresent()){
            PropertyEntity pe = optEnt.get();
            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setPrice(propertyDTO.getPrice());
            pe.setDescription(propertyDTO.getDescription());

            pe = propertyRepository.save(pe);

            propertyDTO = propertyConverter.convertEntityToDTO(pe);
        }else{
            propertyDTO = null;
        }


        return propertyDTO;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity>  optEnt = propertyRepository.findById(propertyId);

        if(optEnt.isPresent()){
            PropertyEntity pe = optEnt.get();
            pe.setDescription(propertyDTO.getDescription());

            pe = propertyRepository.save(pe);

            propertyDTO = propertyConverter.convertEntityToDTO(pe);
        }
        else{
            propertyDTO = null;
        }


        return propertyDTO;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity>  optEnt = propertyRepository.findById(propertyId);

        if(optEnt.isPresent()){
            PropertyEntity pe = optEnt.get();
            pe.setPrice(propertyDTO.getPrice());

            pe = propertyRepository.save(pe);

            propertyDTO = propertyConverter.convertEntityToDTO(pe);
        }
        else{
            propertyDTO = null;
        }

        return propertyDTO;
    }

    @Override
    public PropertyDTO deleteProperty(Long propertyId) {
        Optional<PropertyEntity>  optEnt = propertyRepository.findById(propertyId);
        PropertyDTO propertyDTO;
        if(optEnt.isPresent()){
            PropertyEntity pe = optEnt.get();
            propertyDTO = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.delete(pe);
        }
        else{
            propertyDTO = null;
        }

        return propertyDTO;

    }
}
