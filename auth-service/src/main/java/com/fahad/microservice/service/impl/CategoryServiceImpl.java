package com.fahad.microservice.service.impl;


import com.fahad.microservice.constent.CafeConstants;
import com.fahad.microservice.dto.ErrorResponse;
import com.fahad.microservice.dto.SuccessResponse;
import com.fahad.microservice.dto.response.CategoryDtoRes;
import com.fahad.microservice.feign.ProductFeignClient;
import com.fahad.microservice.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public ResponseEntity<?> addNewCategory(Map<String, String> requestMap) {
        try {
            String response = productFeignClient.addNewCategory(requestMap);
            if (response.equals(CafeConstants.CATEGORY_ADDED))
                return new ResponseEntity<>(new SuccessResponse<>(response), HttpStatus.CREATED);
            else
                return new ResponseEntity<>(new ErrorResponse(CafeConstants.INVALID_DATA), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllCategory(String filterValue) {
        try {
            List<CategoryDtoRes> categoryDtoResList = productFeignClient.getAllCategory();
            return new ResponseEntity<>(new SuccessResponse<>(categoryDtoResList), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<?> updateCategory(Map<String, String> requestMap) {
        try {
            String response = productFeignClient.addNewCategory(requestMap);
            if (response.equals(CafeConstants.CATEGORY_UPDATED))
                return new ResponseEntity<>(new SuccessResponse<>(response), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ErrorResponse(CafeConstants.INVALID_DATA), HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
