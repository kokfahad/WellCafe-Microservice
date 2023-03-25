package com.fahad.productservice.service.impl;


import com.fahad.productservice.constent.CafeConstants;
import com.fahad.productservice.dto.request.DashboardDTO;
import com.fahad.productservice.dto.response.ProductDtoRes;
import com.fahad.productservice.feign.BillFeignClient;
import com.fahad.productservice.mapper.ObjectMapper;
import com.fahad.productservice.model.Category;
import com.fahad.productservice.model.Product;
import com.fahad.productservice.repository.CategoryRepository;
import com.fahad.productservice.repository.ProductRepository;
import com.fahad.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    ObjectMapper<Product, ProductDtoRes> objectMapper;

    @Autowired
    private BillFeignClient billFeignClient;

    @Override
    public String addNewProduct(Map<String, String> requestMap) {
        try {
            if (validateProductMap(requestMap, false)) {
                productRepository.save(getProductFromMap(requestMap, false));
                return CafeConstants.Product_Successfully_Created;
            }
            return CafeConstants.INVALID_DATA;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;
    }


    private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private Product getProductFromMap(Map<String, String> requestMap, boolean isAdd) {
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));

        Product product = new Product();
        if (isAdd)
            product.setId(Integer.parseInt(requestMap.get("id")));
        else
            product.setStatus("true");

        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(requestMap.get("price"));
        return product;
    }

    @Override
    public List<ProductDtoRes> getAllProduct() {
        try {
            List<Product> productList = productRepository.findAll();
            List<ProductDtoRes> productDtoResList = objectMapper.mapAllToDTOList(productList, ProductDtoRes.class);
            return productDtoResList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String updateProduct(Map<String, String> requestMap) {
        try {
            if (validateProductMap(requestMap, true)) {
                Optional<Product> productOptional = productRepository.findById(Integer.parseInt(requestMap.get("id")));
                if (productOptional.isPresent()) {
                    Product product = getProductFromMap(requestMap, true);
                    product.setStatus(productOptional.get().getStatus());
                    productRepository.save(product);
                    return CafeConstants.PRODUCT_UPDATED;
                } else
                    return CafeConstants.PRODUCT_ID_DOES_NOT_EXIST;
            } else {
                return CafeConstants.INVALID_DATA;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;
    }

    @Override
    public String deleteProduct(Integer id) {
        try {
            if (id != null) {
                Optional<Product> product = productRepository.findById(id);
                if (product.isPresent()) {
                    productRepository.deleteById(id);
                    return CafeConstants.PRODUCT_DELETED;
                } else
                    return CafeConstants.PRODUCT_ID_DOES_NOT_EXIST;
            }
            return CafeConstants.INVALID_DATA;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;
    }

    @Override
    public String updateStatus(Map<String, String> requestMap) {
        try {
            Integer id = Integer.parseInt(requestMap.get("id"));
            if (id != null) {
                Optional<Product> product = productRepository.findById(id);
                if (product.isPresent()) {
                    product.get().setStatus(requestMap.get("status"));
//                    productRepository.updateProductStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    productRepository.save(product.get());
                    return CafeConstants.PRODUCT_STATUS_UPDATED;
                } else
                    return CafeConstants.PRODUCT_ID_DOES_NOT_EXIST;
            } else
                return CafeConstants.INVALID_DATA;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;
    }

    @Override
    public List<ProductDtoRes> getByCategory(Integer id) {
        try {
            if (id != null) {
                List<Product> productList = productRepository.findProductsByCategory(id);
                if (productList.size() > 0) {
                    List<ProductDtoRes> productDtoResList = objectMapper.mapAllToDTOList(productList, ProductDtoRes.class);
                    return productDtoResList;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductDtoRes getProductById(Integer id) {
        try {
            if (id != null) {
                Optional<Product> product = productRepository.findById(id);
                if (product.isPresent()) {
                    ProductDtoRes productDtoRes = objectMapper.copyEntityToDTO(product, ProductDtoRes.class).get();
                    return productDtoRes;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public DashboardDTO getDashboardData() {
        try{
            DashboardDTO dashboardDTO = new DashboardDTO();
            String productCount = String.valueOf(productRepository.count());
            String categoryCount = String.valueOf(categoryRepository.count());
            dashboardDTO.setProduct(productCount);
            dashboardDTO.setCategory(categoryCount);

            dashboardDTO = billFeignClient.getTotalBillCount(dashboardDTO);
            return dashboardDTO;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
