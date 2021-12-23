package com.precure.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractResponse<T> {
    protected Integer status;
    protected T data;
    protected String msg;

    public AbstractResponse(T data) {
        this(HttpStatus.OK.value(), data, null);
    }

    public AbstractResponse(Integer status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
