package com.fahad.microservice.service.impl;

import com.fahad.microservice.constent.CafeConstants;
import com.fahad.microservice.dto.ErrorResponse;
import com.fahad.microservice.dto.SuccessResponse;
import com.fahad.microservice.dto.response.BillDtoRes;
import com.fahad.microservice.feign.BillFeignClient;
import com.fahad.microservice.jwt.JwtFilter;
import com.fahad.microservice.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BillServiceImpl implements BillService {

    @Autowired
    private BillFeignClient billFeignClient;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    public ResponseEntity<?> generateReport(Map<String, Object> requestMap) {
        log.info("Inside generateReport");
        try {
            String response = billFeignClient.generateReport(requestMap);
            return new ResponseEntity<>(new SuccessResponse<>(response), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void insertBill(Map<String, Object> requestMap) {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseEntity<?> getBills() {
        try {
            Boolean isAdmin = jwtFilter.isAdmin();
            String currentUser = jwtFilter.getCurrentUser();
            List<BillDtoRes>billDtoRes = billFeignClient.getBills(isAdmin, currentUser);
            return new ResponseEntity<>(new SuccessResponse<>(billDtoRes), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getPDF(Map<String, Object> requestMap) {
        log.info("Inside getPDF");
        log.info("requestMap {}", requestMap);
        try {
            byte[] bytes = billFeignClient.getPDF(requestMap);
            return new ResponseEntity<>(new SuccessResponse<>(bytes), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<?> deleteBill(Integer id) {
        try {
           String response = billFeignClient.deleteBill(id);
           if (response.equals(CafeConstants.BILL_DELETED_SUCCESSFULLY))
               return new ResponseEntity<>(new SuccessResponse<>(response), HttpStatus.OK);
           else
               return new ResponseEntity<>(new ErrorResponse(CafeConstants.INVALID_DATA), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorResponse(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
