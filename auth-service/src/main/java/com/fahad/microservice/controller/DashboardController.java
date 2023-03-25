package com.fahad.microservice.controller;


import com.fahad.microservice.dto.request.DashboardDTO;
import com.fahad.microservice.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/details")
    DashboardDTO getCount() {
        try {
            return dashboardService.getCount();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }
}
