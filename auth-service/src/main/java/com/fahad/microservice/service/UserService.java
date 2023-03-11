package com.fahad.microservice.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    ResponseEntity<?> signUp(Map<String, String> requestMap);
    ResponseEntity<?> login(Map<String, String> requestMap);
    boolean validateSignUpMap(Map<String, String> requestMap);
    ResponseEntity<?> getAllUser();
    ResponseEntity<?> update(Map<String, String> requestMap);
    ResponseEntity<?> checkToken();
    ResponseEntity<?> changePassword(Map<String, String> requestMap);

    String test();
//    ResponseEntity<?> forgotPassword(Map<String, String> requestMap);
}
