package com.fahad.microservice.service.impl;


import com.fahad.microservice.constent.CafeConstants;
import com.fahad.microservice.dto.ErrorResponse;
import com.fahad.microservice.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {



    @Override
    public ResponseEntity<?> addNewCategory(Map<String, String> requestMap) {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllCategory(String filterValue) {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<?> updateCategory(Map<String, String> requestMap) {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
