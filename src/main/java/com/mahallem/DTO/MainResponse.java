package com.mahallem.DTO;


public class MainResponse <T> {
    private T Data;
    private    boolean Success;
    private String ErrorMessage;

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public MainResponse(String errorMessage) {
        ErrorMessage = errorMessage;
        Success=false;
        Data=null;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public MainResponse() {
        ErrorMessage = null;
        Success=true;
        Data=null;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public MainResponse(T data, boolean success) {
        Data = data;
        Success = success;
    }
}
