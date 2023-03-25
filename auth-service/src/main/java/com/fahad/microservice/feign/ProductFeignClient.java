package com.fahad.microservice.feign;

import com.fahad.microservice.dto.response.ProductDtoRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Service
@FeignClient(name = "product-service", decode404 = true)
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/test")
    String test();

    @RequestMapping(method = RequestMethod.POST, value = "/api/product/add", consumes = "application/json")
    String addNewProduct(@RequestBody Map<String, String> requestMap);

    @RequestMapping(method = RequestMethod.POST, value = "/api/product/update", consumes = "application/json")
    String updateProduct(@RequestBody Map<String, String> requestMap);

    @RequestMapping(method = RequestMethod.POST, value = "/api/product/delete/{id}", consumes = "application/json")
    String deleteProduct(@PathVariable("id") Integer id);

    @RequestMapping(method = RequestMethod.POST, value = "/api/product/updateStatus", consumes = "application/json")
    String updateStatus(@RequestBody Map<String, String> requestMap);

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/get")
    List<ProductDtoRes> getAllProduct();

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/get-by-category/{id}")
    List<ProductDtoRes> getByCategory(@PathVariable("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/get-by-id/{id}")
    ProductDtoRes getProductById(@PathVariable("id") Integer id);


}
