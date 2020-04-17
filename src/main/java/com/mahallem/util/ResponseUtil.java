package com.mahallem.util;

import com.mahallem.viewmodel.ErrorResponse;
import com.mahallem.viewmodel.MainResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T> ResponseEntity<MainResponse<T>> data(T data) {

        return MainResponse.response(data);
    }

    public static <T> ResponseEntity<Page<T>> data(Page<T> data) {

        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    public static <T> ResponseEntity<MainResponse<T>> data(T data, HttpStatus httpStatus) {

        return MainResponse.response(data);
    }

    public static <T> ResponseEntity<MainResponse<ErrorResponse>> error(String errorMessage, String errorCode) {

        return MainResponse.errorResponse(errorMessage, errorCode);
    }
}
