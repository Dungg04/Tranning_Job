package com.example.warehousemanagementapi.dtos;

import com.example.warehousemanagementapi.constant.ResponseMessageEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDTO<T> {
    private Integer status;
    private ResponseMessageEnum message;
    private T result;
}