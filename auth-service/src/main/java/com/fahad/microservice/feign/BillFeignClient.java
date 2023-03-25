package com.fahad.microservice.feign;

import com.fahad.microservice.dto.response.BillDtoRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Service
@FeignClient(name = "bill-service", decode404 = true)
public interface BillFeignClient {
    @RequestMapping(method = RequestMethod.POST, value = "/api/bill/generate-report", consumes = "application/json")
    String generateReport(@RequestBody Map<String, Object> requestMap);

    @RequestMapping(method = RequestMethod.POST, value = "/api/bill/getPdf", consumes = "application/json")
    byte[] getPDF(@RequestBody Map<String, Object> requestMap);

    @RequestMapping(method = RequestMethod.POST, value = "/api/bill//delete/{id}", consumes = "application/json")
    String deleteBill(@PathVariable("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/api/bill/get-bills/{isAdmin}/{currentUser}")
    List<BillDtoRes> getBills(@PathVariable("isAdmin") Boolean isAdmin, @PathVariable("currentUser") String currentUser);


}
