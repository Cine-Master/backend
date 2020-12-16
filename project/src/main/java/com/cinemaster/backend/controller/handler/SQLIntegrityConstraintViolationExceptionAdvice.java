package com.cinemaster.backend.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class SQLIntegrityConstraintViolationExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String sqlIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException e) {
        return "Data is not valid";
    }

}
