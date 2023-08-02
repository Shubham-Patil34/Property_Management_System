package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.CalculatorDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")  // url mapping to class
public class CalculatorController {



// METHOD 1: url mapping using @RequestParam
    @GetMapping("/add") //url mapping to function

    //  http://localhost:8080/api/v1/calculator/add?num3=26&num2=2
    public Double add(@RequestParam("num3") Double num1, @RequestParam("num2") Double num2){
    // key value in get request should match to requestParam not to function parameters
        return num1 + num2;
    }

// METHOD 2: url mapping using @PathVariable
    @GetMapping("/sub/{num3}/{num2}") //url mapping to function
//    http://localhost:8080/api/v1/calculator/sub/123/24
    public Double subtract(@PathVariable("num3") Double num1, @PathVariable("num2") Double num2){
        if(num1 > num2)
            return num1 - num2;
        return num2 - num1;
    }

// METHOD 3: url mapping using @RequestParam and @PathVariable combine
    @GetMapping("/max/{num3}") //url mapping to function
    //    http://localhost:8080/api/v1/calculator/max/34?num1=45&num2=75
    public String maxNumber(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2, @PathVariable("num3") Double num3){
        if(num1 > num2 && num1 > num3) {
            return num1 + " is greater";
        }
        else if( num2 > num3 ){
            return num2 + " is greater";
        }
        return num3 + " is greater";
    }

    @PostMapping("/mul")
    public ResponseEntity<Double> multiply(@RequestBody CalculatorDTO calculatorDTO){
        Double result = null;
        result = calculatorDTO.getNum1() *
                calculatorDTO.getNum2() *
                calculatorDTO.getNum3() *
                calculatorDTO.getNum4();
        ResponseEntity<Double> responseEntity = new ResponseEntity<Double>(result, HttpStatus.CREATED);
        return responseEntity;
    }
}
