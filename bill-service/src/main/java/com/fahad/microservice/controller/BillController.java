package com.fahad.microservice.controller;

import com.fahad.microservice.constent.CafeConstants;
import com.fahad.microservice.dto.request.DashboardDTO;
import com.fahad.microservice.dto.response.BillDtoRes;
import com.fahad.microservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/generate-report")
    String generateReport(@RequestBody Map<String, Object> requestMap) {
        try {
            return billService.generateReport(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;
    }

    @GetMapping("/get-bills/{isAdmin}/{currentUser}")
    List<BillDtoRes> getBills(@PathVariable("isAdmin") Boolean isAdmin, @PathVariable("currentUser") String currentUser) {
        try {
            return billService.getBills(isAdmin, currentUser);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @PostMapping("/getPdf")
    byte[] getPDF(@RequestBody Map<String, Object> requestMap) {
        try {
            return billService.getPDF(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @PostMapping("/delete/{id}")
    String deleteBill(@PathVariable Integer id) {
        try {
            return billService.deleteBill(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;
    }

    @PostMapping("/get-bill-count")
    DashboardDTO getBillsCount(@RequestBody DashboardDTO dashboardDTO) {
        try {
            return billService.getBillsCount(dashboardDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }



}
