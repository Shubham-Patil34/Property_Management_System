package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class UserController {

    // Dependency injection
    @Autowired
    private UserService userService;

    //http://localhost:8080/api/v2/register-user
    @PostMapping("/register-user")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO){
        userDTO = userService.registerUser(userDTO);
        String ack = userDTO.getOwnerEmail() +" is registered successfully.";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(ack, HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("/login-user")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO){
        if(userService.loginUser(userDTO))
            return new ResponseEntity<>(userDTO.getOwnerEmail()+" is logged in successfully.", HttpStatus.OK);
        return new ResponseEntity<>(" Invalid User.", HttpStatus.BAD_REQUEST);
    }

}
