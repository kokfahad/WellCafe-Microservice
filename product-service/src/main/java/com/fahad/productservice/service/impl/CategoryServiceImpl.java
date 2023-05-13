package com.fahad.productservice.service.impl;


import com.fahad.productservice.constent.CafeConstants;
import com.fahad.productservice.dto.response.CategoryDtoRes;
import com.fahad.productservice.mapper.ObjectMapper;
import com.fahad.productservice.model.Category;
import com.fahad.productservice.repository.CategoryRepository;
import com.fahad.productservice.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    ObjectMapper<Category, CategoryDtoRes> objectMapper;


    @Override
    public String addNewCategory(Map<String, String> requestMap) {
        try {
            if (validateCategoryMap(requestMap, false)) {
                categoryRepository.save(getCategoryFromMap(requestMap, false));
                return CafeConstants.CATEGORY_ADDED;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;
    }

    @Override
    public List<CategoryDtoRes> getAllCategory(String filterValue) {
        try {
//            if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")){
//                //will fix this code later
//                List<Category> categoryList = categoryRepository.findAll();
//                return new ResponseEntity<List<Category>>( categoryList,HttpStatus.OK);
//            }
            List<Category> categoryList = categoryRepository.findAll();
            List<CategoryDtoRes> categoryDtoResList = objectMapper.mapAllToDTOList(categoryList, CategoryDtoRes.class);
            return categoryDtoResList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }


    private Category getCategoryFromMap(Map<String, String> requestMap, Boolean isAdd) {
        Category category = new Category();
        if (isAdd) {
            category.setId(Integer.parseInt(requestMap.get("id")));
        }
        category.setName(requestMap.get("name"));
        return category;
    }

    @Override
    public String updateCategory(Map<String, String> requestMap) {
        try {
            if (validateCategoryMap(requestMap, false)) {
                Optional<Category> category = categoryRepository.findById(Integer.parseInt(requestMap.get("id")));
                if (category.isPresent()) {
                    categoryRepository.save(getCategoryFromMap(requestMap, true));
                    return CafeConstants.CATEGORY_UPDATED;
                } else {
                    return CafeConstants.CATEGORY_ID_DOES_NOT_EXIST;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;
    }


}
