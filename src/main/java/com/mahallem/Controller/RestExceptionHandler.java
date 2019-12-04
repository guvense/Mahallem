package com.mahallem.Controller;

import com.mahallem.Exception.BaseException;
import com.mahallem.ViewModel.RestMessage;
import org.springframework.context.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.mahallem.Exception.BaseValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Locale;

@ControllerAdvice
public class RestExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    private final MessageSource messageSource;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> handleIllegalArgument(BaseException ex, Locale locale) {

        String errorMessage = messageSource.getMessage(ex.getMessage(), null, locale);
        LOGGER.info(errorMessage);


        return new ResponseEntity<>(errorMessage, HttpStatus.OK);
    }

    @ExceptionHandler(BaseValidationException.class)
    public ResponseEntity<String> validationException(BaseValidationException ex, Locale locale) {

        String errorMessage = messageSource.getMessage(ex.getMessage(), null, locale);
        LOGGER.info(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception ex, Locale locale) {

        String errorMessage = messageSource.getMessage(ex.getMessage(), null, locale);
        LOGGER.info(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.OK);

    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, Locale locale) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
