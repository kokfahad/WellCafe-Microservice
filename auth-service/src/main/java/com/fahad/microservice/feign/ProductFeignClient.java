package com.fahad.microservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(name = "product-service")
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/test")
    String test();


}
