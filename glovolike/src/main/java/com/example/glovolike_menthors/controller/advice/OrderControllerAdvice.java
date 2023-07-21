package com.example.glovolike_menthors.controller.advice;

import com.example.glovolike_menthors.controller.v1.OrderController;
import com.example.glovolike_menthors.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice(basePackageClasses = OrderController.class)
public class OrderControllerAdvice {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e){
        return new ErrorResponse().setHttpStatus(HttpStatus.BAD_REQUEST).setMessage(e.getLocalizedMessage());
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ErrorResponse handleNoSuchElementException(NoSuchElementException e){
        return new ErrorResponse().setHttpStatus(HttpStatus.BAD_REQUEST).setMessage(e.getLocalizedMessage());
    }
}
