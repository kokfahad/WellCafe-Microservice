package com.fahad.microservice.feign;

import com.fahad.microservice.dto.request.DashboardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(name = "report-service", decode404 = true)
public interface ReportFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/dashboard/details")
    DashboardDTO getDashboardData();
}
