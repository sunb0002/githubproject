package com.precure.controllers;

public class GeneralResponse extends AbstractResponse<String> {
    public GeneralResponse(String data) {
        super(data);
    }

    public GeneralResponse(Integer status, String data, String msg) {
        super(status, data, msg);
    }
}
