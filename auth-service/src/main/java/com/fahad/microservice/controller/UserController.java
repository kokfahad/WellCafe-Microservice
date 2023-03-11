package com.fahad.microservice.controller;


import com.fahad.microservice.constent.CafeConstants;
import com.fahad.microservice.feign.ProductFeignClient;
import com.fahad.microservice.jwt.JwtFilter;
import com.fahad.microservice.request.UserDTO;
import com.fahad.microservice.service.UserService;
import com.fahad.microservice.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtFilter jwtFilter;

    @Lazy
    @Autowired
    private ProductFeignClient productFeignClient;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Map<String, String> requestMap){
       try {
           return  userService.signUp(requestMap);
       }catch (Exception ex){
           ex.printStackTrace();
       }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> requestMap){
        try {
            return  userService.login(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUser(){
        try {
            if (jwtFilter.isAdmin())
               return userService.getAllUser();
            else {
                return new ResponseEntity<>(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception ex){ex.printStackTrace();
        }
        return new ResponseEntity<List<UserDTO>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/update-status")
    public ResponseEntity<?> update(@RequestBody Map<String, String> requestMap ){
        try {
            if (jwtFilter.isAdmin()){
              return  userService.update(requestMap);
            }else {
                return new ResponseEntity<>(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/checkToken")
    ResponseEntity<?> checkToken(){
        try {
            return userService.checkToken();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/change-password")
    ResponseEntity<?> changePassword(@RequestBody Map<String, String> requestMap){
        try {
            return userService.changePassword(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @PostMapping("/forgot-password")
//    ResponseEntity<?> forgotPassword(@RequestBody Map<String, String>requestMap){
//        try {
//            userService.forgotPassword(requestMap);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @GetMapping("/test")
    String test(){
        return userService.test();
    }

}
