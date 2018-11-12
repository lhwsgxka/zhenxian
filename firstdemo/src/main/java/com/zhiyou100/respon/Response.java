package com.zhiyou100.respon;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Response {
    //成功
    public static String responseSucceed(int code,String message){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(code);
        responseMessage.setSuccess(true);
        responseMessage.setMessage(message);
        String toJson = new Gson().toJson(responseMessage);
        return toJson;
    }

    public static String responseError(int code,String error){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setError(error);
        responseMessage.setCode(code);
        responseMessage.setSuccess(false);
        String toJson = new Gson().toJson(responseMessage);
        return toJson;
    }
}
