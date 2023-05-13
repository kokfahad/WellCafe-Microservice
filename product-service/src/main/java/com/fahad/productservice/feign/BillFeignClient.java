package com.fahad.productservice.feign;


import com.fahad.productservice.dto.request.DashboardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(name = "bill-service", decode404 = true)
public interface BillFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/bill/get-bill-count")
    DashboardDTO getTotalBillCount(@RequestBody DashboardDTO dashboardDTO);


}
