package com.zhiyou100.respon;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {
    private int code;
    private boolean isSuccess;
    private String message;
    private String error;

    public ResponseMessage() {
    }
}
