package com.mahallem.Controller;

import com.mahallem.Exception.BaseException;

import com.mahallem.Exception.ExceptionCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class RestExceptionHandler {

    Logger logger= LoggerFactory.getLogger(getClass());

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> handleIllegalArgument(BaseException ex, Locale locale) {

        logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception ex, Locale locale) {

        logger.error(ex.getMessage());
        String a= Integer.toString(ExceptionCode.UNEXPECTED_ERROR);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
}
