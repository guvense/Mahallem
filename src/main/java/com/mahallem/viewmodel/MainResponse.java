package com.mahallem.viewmodel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class MainResponse<T> {

    private T data;
    private ErrorResponse errorMessage;
    private boolean success;


    private MainResponse(String errMessage, String errorCode) {
        this.errorMessage = new ErrorResponse(errMessage,errorCode);
    }

    private MainResponse(Boolean success) {
        this.success = success;
    }
    private MainResponse(T data) {
        this.data = data;
        this.success = true;
    }

    public static <T> ResponseEntity<MainResponse<ErrorResponse>> errorResponse(String errorMessage, String errorCode) {

        MainResponse<ErrorResponse> objectMainResponse = new MainResponse<>(errorMessage, errorCode);
        return new ResponseEntity<>(objectMainResponse, HttpStatus.OK);
    }

    public static <T> ResponseEntity<MainResponse<T>> response(T data) {

        MainResponse<T> response = new MainResponse<>(data);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public static ResponseEntity<MainResponse> response(Boolean status) {

        MainResponse<Object> response = new MainResponse<>(status);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
