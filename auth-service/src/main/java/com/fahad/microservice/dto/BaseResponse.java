package com.fahad.microservice.dto;

import lombok.Data;

@Data
public class BaseResponse<T> {
    protected boolean isSuccess;
}
