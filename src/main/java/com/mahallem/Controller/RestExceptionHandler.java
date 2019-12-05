package com.mahallem.Controller;

import com.mahallem.Exception.BaseException;
import org.springframework.context.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.mahallem.Exception.BaseValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
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
        return new ResponseEntity<>(errorMessage, HttpStatus.OK);
    }

    @ExceptionHandler(BaseValidationException.class)
    public ResponseEntity<String> validationException(BaseValidationException ex, Locale locale) {

        String errorMessage = messageSource.getMessage(ex.getMessage(), null, locale);
        return new ResponseEntity<>(errorMessage, HttpStatus.OK);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> validationException(BindException ex, Locale locale) {
        String errorMessage = ex.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));

        return new ResponseEntity<>(errorMessage, HttpStatus.OK);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception ex, Locale locale) {

        return new ResponseEntity<>("Undefined Exception", HttpStatus.OK);

    }

}
