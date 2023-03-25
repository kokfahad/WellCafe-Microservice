package com.fahad.productservice.dto;

import lombok.Data;

@Data
public class BaseResponse<T> {
    protected boolean isSuccess;
}
