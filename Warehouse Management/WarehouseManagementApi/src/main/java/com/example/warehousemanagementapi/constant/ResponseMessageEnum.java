package com.example.warehousemanagementapi.constant;


public enum ResponseMessageEnum {
    SUCCESS ("Response success"),
    ERROR ("Response error");
    private String message;

    ResponseMessageEnum() {
    }

    ResponseMessageEnum(String message) {
        this.message = message;
    }
}
