package com.example.srms.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private boolean error;
    private int code;
    private T data;
    private List<T> dataList;
    private String message;

    public Response(boolean error, int code, String message) {
        this.error = error;
        this.code = code;
        this.message = message;
    }

    public Response(boolean error, int code, T data) {
        this.error = error;
        this.code = code;
        this.data = data;
    }

    public Response(boolean error, int code, List<T> dataList, String message) {
        this.error = error;
        this.code = code;
        this.dataList = dataList;
        this.message = message;
    }

    public Response(boolean error, int code, T data, String message) {
        this.error = error;
        this.code = code;
        this.data = data;
        this.message = message;
    }
}
