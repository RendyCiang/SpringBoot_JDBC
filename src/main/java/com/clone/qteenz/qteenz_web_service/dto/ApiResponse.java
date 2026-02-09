package com.clone.qteenz.qteenz_web_service.dto;

public class ApiResponse<T> {
    private  String status;
    private int code;
    private String mess;
    private T data;

    public ApiResponse(String status, int code, String mess, T data) {
        this.status = status;
        this.code = code;
        this.mess = mess;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
