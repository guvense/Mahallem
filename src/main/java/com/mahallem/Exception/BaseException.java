package com.mahallem.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class BaseException extends RuntimeException {

     BaseException(int code){
        super(String.valueOf(code));
    }


}
