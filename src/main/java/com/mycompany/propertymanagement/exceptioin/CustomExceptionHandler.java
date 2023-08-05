package com.mycompany.propertymanagement.exceptioin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ExcludeDefaultListeners;

@ControllerAdvice
public class CustomExceptionHandler{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv){
        List<ErrorModel> errorModels = new ArrayList<>();
        ErrorModel errorModel = null;
        List<FieldError> fieldErrorList = manv.getBindingResult().getFieldErrors();

        for (FieldError fe : fieldErrorList) {
            logger.info("Inside field validation: {} - {}", fe.getField(), fe.getDefaultMessage());
            logger.debug("Inside field validation: {} - {}", fe.getField(), fe.getDefaultMessage());
            errorModel = new ErrorModel();
            errorModel.setCode(fe.getCode());
            errorModel.setMessage(fe.getDefaultMessage());
            errorModels.add(errorModel);
        }

        return new ResponseEntity<List<ErrorModel>>(errorModels, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException businessException){
//        System.out.println("Error: Business exception");
        for(ErrorModel em: businessException.getErrors()){
            logger.debug("Inside business exception: {} - {}", em.getCode(), em.getMessage());
            logger.info("Inside business exception: {} - {}", em.getCode(), em.getMessage());
            logger.warn("Inside business exception: {} - {}", em.getCode(), em.getMessage());
            logger.error("Inside business exception: {} - {}", em.getCode(), em.getMessage());
        }

        return new ResponseEntity<List<ErrorModel>>(businessException.getErrors(), HttpStatus.BAD_REQUEST);
    }

}
