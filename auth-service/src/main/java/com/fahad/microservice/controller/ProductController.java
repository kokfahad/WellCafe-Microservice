package com.fahad.microservice.controller;


import com.fahad.microservice.constent.CafeConstants;
import com.fahad.microservice.dto.ErrorResponse;
import com.fahad.microservice.jwt.JwtFilter;
import com.fahad.microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private JwtFilter jwtFilter;

    @PostMapping("/add")
    public ResponseEntity<?> addNewProduct(@RequestBody Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin())
                return productService.addNewProduct(requestMap);
            else
                return new ResponseEntity<>(new ErrorResponse(CafeConstants.UNAUTHORIZED_ACCESS), HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/update")
    ResponseEntity<?> updateProduct(@RequestBody Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin())
                return productService.updateProduct(requestMap);
            else
                return new ResponseEntity<>(new ErrorResponse(CafeConstants.UNAUTHORIZED_ACCESS), HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/delete/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
                return productService.deleteProduct(id);
            } else
                return new ResponseEntity<>(new ErrorResponse(CafeConstants.UNAUTHORIZED_ACCESS), HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin())
                return productService.updateStatus(requestMap);
            else
                return new ResponseEntity<>(new ErrorResponse(CafeConstants.UNAUTHORIZED_ACCESS), HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get")
    ResponseEntity<?> getAllProduct() {
        try {
            return productService.getAllProduct();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get-by-category/{id}")
    public ResponseEntity<?> getByCategory(@PathVariable("id") Integer id){
        try {
            return productService.getByCategory(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id){
        try {
            return productService.getProductById(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
