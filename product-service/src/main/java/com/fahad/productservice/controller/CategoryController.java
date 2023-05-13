package com.fahad.productservice.controller;


import com.fahad.productservice.constent.CafeConstants;
import com.fahad.productservice.dto.response.CategoryDtoRes;
import com.fahad.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    String addNewCategory(@RequestBody Map<String, String> requestMap) {
        try {
            return categoryService.addNewCategory(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;
    }

    @GetMapping("/get")
    List<CategoryDtoRes> getAllCategory(@RequestParam(required = false) String filterValue) {
        try {
            return categoryService.getAllCategory(filterValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @PostMapping("/update")
    String updateCategory(@RequestBody Map<String, String> requestMap) {
        try {
            return categoryService.updateCategory(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;
    }
}
