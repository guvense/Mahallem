package com.mahallem.viewmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainResponse <T>  {
    private T data;
    private boolean success;
    private String errorMessage;
    private int errorCode;


    public MainResponse(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode= errorCode;
        this.success=false;
        this.data=null;
    }

    public MainResponse(T data) {
        this.data = data;
        this.success = true;
    }
}