package com.fahad.microservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Service
@FeignClient(name = "product-service", decode404 = true)
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/test")
    String test();

    @RequestMapping(method = RequestMethod.POST, value = "/api/product/add", consumes = "application/json")
    ResponseEntity<?> addNewProduct(@RequestBody Map<String, String> requestMap);

    @RequestMapping(method = RequestMethod.POST, value = "/api/product/update", consumes = "application/json")
    ResponseEntity<?> updateProduct(@RequestBody Map<String, String> requestMap);

    @RequestMapping(method = RequestMethod.POST, value = "/api/product/delete/{id}", consumes = "application/json")
    ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id);

    @RequestMapping(method = RequestMethod.POST, value = "/api/product/updateStatus", consumes = "application/json")
    ResponseEntity<?> updateStatus(@RequestBody Map<String, String> requestMap);

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/get")
    ResponseEntity<?> getAllProduct();

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/get-by-category/{id}")
    ResponseEntity<?> getByCategory(@PathVariable("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/get-by-id/{id}")
    ResponseEntity<?> getProductById(@PathVariable("id") Integer id);


}
