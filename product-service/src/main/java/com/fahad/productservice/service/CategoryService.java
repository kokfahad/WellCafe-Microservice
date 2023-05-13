package com.fahad.productservice.service;

import com.fahad.productservice.dto.response.CategoryDtoRes;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    String addNewCategory(Map<String, String> requestMap);
    List<CategoryDtoRes> getAllCategory(String filterValue);
    String updateCategory(Map<String, String> requestMap);
}
