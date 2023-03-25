package com.fahad.productservice.feign;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomFeignClientInterceptor implements RequestInterceptor {

//    @Autowired
//    private CommonUtils commonUtils;
//    @Autowired
//    private ServiceProperties serviceProperties;

    @Override
    public void apply(RequestTemplate template) {

//        commonUtils.getJWTTokenFromRequestContext()
//                .ifPresent(token -> template.header(
//                        serviceProperties.getAuthorizationHeader(), token));
//
//        commonUtils.getCorrelationIdFromRequestContext()
//                .ifPresent(corID -> template.header(serviceProperties.getCorrelationId(), corID));
//
//        commonUtils.getUserFromRequestContext()
//                .ifPresent(user -> template.header(serviceProperties.getUserId(), user));
    }
}
