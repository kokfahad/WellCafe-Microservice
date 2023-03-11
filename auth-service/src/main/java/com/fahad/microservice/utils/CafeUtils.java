package com.fahad.microservice.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@Slf4j
public class CafeUtils {

    private CafeUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage ,
                                                           HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\"" + responseMessage + "\"}" , httpStatus);
    }

    public static String getUUID(){
        Date date = new Date();
        long time = date.getTime();
        return "BILL-" + time;
    }


}
