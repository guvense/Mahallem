package com.mahallem.viewmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainResponse<T> {
    private T data;
    private ErrorResponse errorMessage;
    private boolean success;



    private MainResponse(String errMessage, String errorCode) {
        this.errorMessage = new ErrorResponse(errMessage,errorCode);
    }

    private MainResponse(T data) {
        this.data = data;
        this.success = true;
    }

    public static <T> MainResponse<ErrorResponse> errorResponse(String errorMessage, String errorCode) {
        return new MainResponse<>(errorMessage, errorCode);
    }

    public static <T> MainResponse<T> response(T data) {
        return new MainResponse<>(data);
    }
}
