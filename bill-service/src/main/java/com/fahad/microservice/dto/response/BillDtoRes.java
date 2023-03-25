package com.fahad.microservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDtoRes {

    private Integer id;

    private String uuid;

    private String name;

    private String contactNumber;

    private String email;

    private String paymentMethod;

    private Integer total;

    private String productDetails;

    private String createdBy;
}
