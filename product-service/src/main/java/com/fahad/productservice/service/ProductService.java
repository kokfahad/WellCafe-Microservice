package com.fahad.productservice.service;

import com.fahad.productservice.dto.request.DashboardDTO;
import com.fahad.productservice.dto.response.ProductDtoRes;

import java.util.List;
import java.util.Map;


public interface ProductService {
    String addNewProduct(Map<String, String> requestMap);

    List<ProductDtoRes> getAllProduct();

    String updateProduct(Map<String, String> requestMap);


    String deleteProduct(Integer id);

    String updateStatus(Map<String, String>requestMap);

    List<ProductDtoRes> getByCategory(Integer id);

    ProductDtoRes getProductById(Integer id);

    DashboardDTO getDashboardData();
}
