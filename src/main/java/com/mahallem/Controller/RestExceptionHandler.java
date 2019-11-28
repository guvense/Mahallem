package com.mahallem.Controller;

import com.mahallem.Exception.BaseException;
import com.mahallem.Util.RestMessage;
import org.springframework.context.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.mahallem.Exception.BaseValidationException;
import com.mahallem.Exception.ExceptionCode;
import com.mahallem.Exception.PhoneValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;
import java.util.Locale;

@ControllerAdvice
public class RestExceptionHandler {
    private final MessageSource messageSource;
    Logger logger= LoggerFactory.getLogger(getClass());


    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> handleIllegalArgument(BaseException ex, Locale locale) {
        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);
        logger.error(ex.getMessage());
        return new ResponseEntity<>(new RestMessage(errorMessage), HttpStatus.OK);
    }
    @ExceptionHandler(BaseValidationException.class)
    public ResponseEntity<String>  validationException(BaseValidationException ex, Locale locale) {

        logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception ex, Locale locale) {
        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);
        logger.error(ex.getMessage());
        String a= Integer.toString(ExceptionCode.UNEXPECTED_ERROR);
        return new ResponseEntity<>(new RestMessage(errorMessage), HttpStatus.OK);

    }


}
