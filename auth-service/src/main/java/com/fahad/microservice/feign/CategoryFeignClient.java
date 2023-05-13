//package com.fahad.microservice.feign;
//
//import com.fahad.microservice.dto.response.CategoryDtoRes;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//import java.util.Map;
//
//@Service
//@FeignClient(name = "product-service", decode404 = true)
//public interface CategoryFeignClient {
//
//    @RequestMapping(method = RequestMethod.GET, value = "/api/category/get")
//    List<CategoryDtoRes> getAllCategory();
//
//    @RequestMapping(method = RequestMethod.POST, value = "/api/category/add", consumes = "application/json")
//    String addNewCategory(@RequestBody Map<String, String> requestMap);
//
//    @RequestMapping(method = RequestMethod.POST, value = "/api/category/update", consumes = "application/json")
//    String updateCategory(@RequestBody Map<String, String> requestMap);
//}
