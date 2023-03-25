package com.fahad.productservice.controller;


import com.fahad.productservice.constent.CafeConstants;
import com.fahad.productservice.dto.request.DashboardDTO;
import com.fahad.productservice.dto.response.ProductDtoRes;
import com.fahad.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping("/add")
    public String addNewProduct(@RequestBody Map<String, String> requestMap) {
        try {
            return productService.addNewProduct(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;

    }


    @GetMapping("/get")
    List<ProductDtoRes> getAllProduct() {
        try {
            return productService.getAllProduct();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @PostMapping("/update")
    String updateProduct(@RequestBody Map<String, String> requestMap) {
        try {
            return productService.updateProduct(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;

    }

    @PostMapping("/delete/{id}")
    String deleteProduct(@PathVariable Integer id) {
        try {
            return productService.deleteProduct(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestBody Map<String, String> requestMap) {
        try {
            return productService.updateStatus(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;
    }

    @GetMapping("/get-by-category/{id}")
    public List<ProductDtoRes> getByCategory(@PathVariable("id") Integer id) {
        try {
            return productService.getByCategory(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/get-by-id/{id}")
    public ProductDtoRes getProductById(@PathVariable Integer id) {
        try {
            return productService.getProductById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/get-dashboard-data")
    public DashboardDTO getDashboardData() {
        try {
            return productService.getDashboardData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
