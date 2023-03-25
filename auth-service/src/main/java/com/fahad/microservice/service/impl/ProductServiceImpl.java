package com.fahad.microservice.service.impl;


import com.fahad.microservice.constent.CafeConstants;
import com.fahad.microservice.dto.ErrorResponse;
import com.fahad.microservice.dto.SuccessResponse;
import com.fahad.microservice.dto.response.ProductDtoRes;
import com.fahad.microservice.feign.ProductFeignClient;
import com.fahad.microservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductFeignClient productFeignClient;


    @Override
    public ResponseEntity<?> addNewProduct(Map<String, String> requestMap) {
        try {
            String response = productFeignClient.addNewProduct(requestMap);
            if (response.equals(CafeConstants.Product_Successfully_Created))
                return new ResponseEntity<>(new SuccessResponse<>(response), HttpStatus.CREATED);
            else
                return new ResponseEntity<>(new ErrorResponse(CafeConstants.INVALID_DATA), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<?> getAllProduct() {
        try {
            List<ProductDtoRes> productDtoResList = productFeignClient.getAllProduct();
            return new ResponseEntity<>(new SuccessResponse<>(productDtoResList), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateProduct(Map<String, String> requestMap) {
        try {
            String response = productFeignClient.updateProduct(requestMap);
            if (response.equals(CafeConstants.PRODUCT_UPDATED))
                return new ResponseEntity<>(new SuccessResponse<>(response), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ErrorResponse(CafeConstants.INVALID_DATA), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> deleteProduct(Integer id) {
        try {
            String response = productFeignClient.deleteProduct(id);
            if (response.equals(CafeConstants.PRODUCT_DELETED))
                return new ResponseEntity<>(new SuccessResponse<>(response), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ErrorResponse(CafeConstants.INVALID_DATA), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateStatus(Map<String, String> requestMap) {
        try {
            String response = productFeignClient.updateStatus(requestMap);
            if (response.equals(CafeConstants.PRODUCT_STATUS_UPDATED))
                return new ResponseEntity<>(new SuccessResponse<>(response), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ErrorResponse(CafeConstants.INVALID_DATA), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getByCategory(Integer id) {
        try {
            List<ProductDtoRes> productDtoResList = productFeignClient.getByCategory(id);
            return new ResponseEntity<>(new SuccessResponse<>(productDtoResList), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getProductById(Integer id) {
        try {
            ProductDtoRes productDtoRes = productFeignClient.getProductById(id);
            return new ResponseEntity<>(new SuccessResponse<>(productDtoRes), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
