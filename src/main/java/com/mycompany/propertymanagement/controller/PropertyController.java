package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1")

public class PropertyController {
    @Value("${pms.dummy}")
    private String dummyValue;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    // Dependency injection
    @Autowired
    private PropertyService propertyService;

    //http://localhost:8080/api/v1/properties/hello
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO){
        propertyDTO = propertyService.saveProperty(propertyDTO);
//        System.out.println(propertyDTO);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/properties")     // SAME END point but different type non issue
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){
//        System.out.println(dbUrl);
//        System.out.println(dummyValue);
        List<PropertyDTO> propertyList = propertyService.getAllProperties();
        ResponseEntity<List<PropertyDTO>> responseEntity= new ResponseEntity<List<PropertyDTO>>(propertyList, HttpStatus.OK);

        return  responseEntity;
    }

    @GetMapping("/properties/users/{userId}")
    public ResponseEntity<List<PropertyDTO>> getAllPropertiesForUser(@PathVariable Long userId){
//        System.out.println(dbUrl);
//        System.out.println(dummyValue);
        List<PropertyDTO> propertyList = propertyService.getAllPropertiesForUser(userId);
        ResponseEntity<List<PropertyDTO>> responseEntity= new ResponseEntity<List<PropertyDTO>>(propertyList, HttpStatus.OK);

        return  responseEntity;
    }

    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);
        if(propertyDTO == null){
            ResponseEntity<PropertyDTO> responseEntity= new ResponseEntity<PropertyDTO>(propertyDTO, HttpStatus.NOT_FOUND);

            return  responseEntity;
        }
        ResponseEntity<PropertyDTO> responseEntity= new ResponseEntity<PropertyDTO>(propertyDTO, HttpStatus.OK);

        return  responseEntity;
    }

    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        propertyDTO = propertyService.updatePropertyDescription(propertyDTO, propertyId);
        if(propertyDTO == null){
            ResponseEntity<PropertyDTO> responseEntity= new ResponseEntity<PropertyDTO>(propertyDTO, HttpStatus.NOT_FOUND);

            return  responseEntity;
        }
        ResponseEntity<PropertyDTO> responseEntity= new ResponseEntity<PropertyDTO>(propertyDTO, HttpStatus.OK);

        return  responseEntity;
    }

    @PatchMapping("/properties/update-price/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        propertyDTO = propertyService.updatePropertyPrice(propertyDTO, propertyId);
        if(propertyDTO == null){
            ResponseEntity<PropertyDTO> responseEntity= new ResponseEntity<PropertyDTO>(propertyDTO, HttpStatus.NOT_FOUND);

            return  responseEntity;
        }
        ResponseEntity<PropertyDTO> responseEntity= new ResponseEntity<PropertyDTO>(propertyDTO, HttpStatus.OK);

        return  responseEntity;
    }

    @DeleteMapping("/properties/delete-property/{propertyId}")
    public ResponseEntity<PropertyDTO> deleteProperty(@PathVariable Long propertyId){
        PropertyDTO propertyDTO = propertyService.deleteProperty(propertyId);
        if(propertyDTO == null){
            ResponseEntity<PropertyDTO> responseEntity= new ResponseEntity<PropertyDTO>(propertyDTO, HttpStatus.NOT_FOUND);

            return  responseEntity;
        }
        ResponseEntity<PropertyDTO> responseEntity= new ResponseEntity<PropertyDTO>(propertyDTO, HttpStatus.OK);

        return  responseEntity;
    }

}
