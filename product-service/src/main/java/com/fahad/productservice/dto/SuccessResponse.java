package com.fahad.productservice.dto;

import lombok.Data;

@Data
public class SuccessResponse<T> extends BaseResponse<T> {

    public T replyMessage;

    public SuccessResponse() {
        this.isSuccess = true;
    }

    public SuccessResponse(T message) {
        this.isSuccess = true;
        this.replyMessage = message;
    }

    public SuccessResponse(boolean status, T message) {
        this.isSuccess = status;
        this.replyMessage = message;
    }

}
