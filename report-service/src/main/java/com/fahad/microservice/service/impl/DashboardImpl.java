package com.fahad.microservice.service.impl;

import com.fahad.microservice.dto.DashboardDTO;
import com.fahad.microservice.feign.ProductFeignClient;
import com.fahad.microservice.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardImpl implements DashboardService {
    @Autowired
    private ProductFeignClient productFeignClient;


    @Override
    public DashboardDTO getCount() {
        try {
           DashboardDTO dashboardDTO = productFeignClient.getProductDashboardData();
           return dashboardDTO;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
