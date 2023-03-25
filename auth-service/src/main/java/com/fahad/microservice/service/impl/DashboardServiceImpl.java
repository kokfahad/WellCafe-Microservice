package com.fahad.microservice.service.impl;

import com.fahad.microservice.dto.request.DashboardDTO;
import com.fahad.microservice.feign.ReportFeignClient;
import com.fahad.microservice.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private ReportFeignClient reportFeignClient;

    @Override
    public DashboardDTO getCount() {
        try {
            return reportFeignClient.getDashboardData();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
