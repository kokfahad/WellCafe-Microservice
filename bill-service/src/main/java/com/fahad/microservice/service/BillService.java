package com.fahad.microservice.service;

import com.fahad.microservice.dto.request.DashboardDTO;
import com.fahad.microservice.dto.response.BillDtoRes;

import java.util.List;
import java.util.Map;

public interface BillService {
    String generateReport(Map<String, Object> requestMap);

    List<BillDtoRes> getBills(Boolean isAdmin, String currentUser);

    byte[] getPDF(Map<String, Object> requestMap);

    String deleteBill(Integer id);

    DashboardDTO getBillsCount(DashboardDTO dashboardDTO);
}
