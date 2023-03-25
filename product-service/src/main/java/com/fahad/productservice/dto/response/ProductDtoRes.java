package com.fahad.productservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoRes {
    private Integer id;

    private String name;

    private String description;

    private String price;

//    private CategoryDtoRes category;

    private String status;

}
