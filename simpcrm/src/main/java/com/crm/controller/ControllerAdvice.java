package com.crm.controller;

import com.crm.dto.ErrorDTO;
import com.crm.exception.PermissionDeniedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(PermissionDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handlePermissionDeniedException(PermissionDeniedException exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setStatus(HttpStatus.UNAUTHORIZED.value());
        return errorDTO;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleEntityNotFoundException(EntityNotFoundException exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Пользователь с указанными логином и паролем не найден");
        errorDTO.setStatus(HttpStatus.UNAUTHORIZED.value());
        return errorDTO;
    }
}
