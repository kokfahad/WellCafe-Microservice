package com.fahad.microservice.service.impl;

import com.fahad.microservice.constent.CafeConstants;
import com.fahad.microservice.dto.request.DashboardDTO;
import com.fahad.microservice.dto.response.BillDtoRes;
import com.fahad.microservice.mapper.ObjectMapper;
import com.fahad.microservice.model.Bill;
import com.fahad.microservice.repository.BillRepository;
import com.fahad.microservice.service.BillService;
import com.fahad.microservice.utils.CafeUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    ObjectMapper<Bill, BillDtoRes> objectMapper;

    @Override
    public String generateReport(Map<String, Object> requestMap) {
        log.info("Inside generateReport");
        try {
            String fileName;
            if (validateRequestMap(requestMap)) {
                if (requestMap.containsKey("isGenerate") && !(Boolean) requestMap.get("isGenerate")) {
                    fileName = (String) requestMap.get("uuid");
                } else {
                    fileName = CafeUtils.getUUID();
                    requestMap.put("uuid", fileName);
                    insertBill(requestMap);
                }
                String data = "Name: " + requestMap.get("name") + "\n" + "Contact Number : " + requestMap.get("contactNumber") + "\n" + "Email: "
                        + requestMap.get("email") + "\n" + "Payment Method: " + requestMap.get("paymentMethod");

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(CafeConstants.STORE_LOCATION + "\\" + fileName + ".pdf"));

                document.open();
                setRectanglePdf(document);

                Paragraph chunk = new Paragraph("Cafe Management System", getFont("Header"));
                chunk.setAlignment(Element.ALIGN_CENTER);
                document.add(chunk);

                Paragraph paragraph = new Paragraph(data + "\n \n", getFont("Data"));
                document.add(paragraph);

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                addTableHeader(table);

                JSONArray jsonArray = CafeUtils.getJsonArrayFromString((String) requestMap.get("productDetails"));

                for (int i = 0; i < jsonArray.length(); i++) {
                    addRows(table, CafeUtils.getMapFromJson(jsonArray.getString(i)));
                }

                document.add(table);
                Paragraph footer = new Paragraph("Total : " + requestMap.get("totalAmount") + "\n"
                        + "Thank you for visiting. Please visit again", getFont("Data"));
                document.add(footer);
                document.close();
                return "{\"uuid\":\"" + fileName + "\"}";
            }
            return "Required data not found !!";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeConstants.SOMETHING_WENT_WRONG;
    }


    private void addRows(PdfPTable table, Map<String, Object> data) {
        log.info("Inside addRows");
        table.addCell((String) data.get("name"));
        table.addCell((String) data.get("category"));
        table.addCell((String) data.get("quantity"));
        String price = data.get("price").toString();
        table.addCell(price);
//        table.addCell(Double.toString((Double) data.get("price")));
        table.addCell(Double.toString((Double) data.get("total")));
    }

    private void addTableHeader(PdfPTable table) {
        log.info("Inside addTableHeader");
        Stream.of("Name", "Category", "Quantity", "Price", "Sub Total")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setBackgroundColor(BaseColor.ORANGE);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private Font getFont(String type) {
        log.info("Inside getFont");
        switch (type) {
            case "Header":
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "Data":
                Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
                dataFont.setStyle(Font.BOLD);
                return dataFont;
            default:
                return new Font();
        }
    }

    private void setRectanglePdf(Document document) throws DocumentException {
        log.info("Inside setRectanglePdf");
        Rectangle rectangle = new Rectangle(577, 825, 18, 15);
        rectangle.enableBorderSide(1);
        rectangle.enableBorderSide(2);
        rectangle.enableBorderSide(4);
        rectangle.enableBorderSide(8);
        rectangle.setBorderColor(BaseColor.BLACK);
        rectangle.setBorderWidth(1);
        document.add(rectangle);
    }


    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("name") &&
                requestMap.containsKey("contactNumber") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("paymentMethod") &&
                requestMap.containsKey("productDetails") &&
                requestMap.containsKey("totalAmount");
    }

    private void insertBill(Map<String, Object> requestMap) {
        try {
            Bill bill = new Bill();
            bill.setUuid((String) requestMap.get("uuid"));
            bill.setName((String) requestMap.get("name"));
            bill.setEmail((String) requestMap.get("email"));
            bill.setContactNumber((String) requestMap.get("contactNumber"));
            bill.setPaymentMethod((String) requestMap.get("paymentMethod"));
            bill.setTotal(Integer.parseInt((String) requestMap.get("totalAmount")));
            bill.setProductDetails((String) requestMap.get("productDetails"));
            bill.setCreatedBy((String) requestMap.get("email"));
            billRepository.save(bill);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<BillDtoRes> getBills(Boolean isAdmin, String currentUser) {
        try {
            List<Bill> list = new ArrayList<>();
            if (isAdmin) {
                list = billRepository.getAllBills();
                List<BillDtoRes> billDtoResList = objectMapper.mapAllToDTOList(list, BillDtoRes.class);
                return billDtoResList;
            } else {
                list = billRepository.findByCreatedByOrderByIdDesc(currentUser);
                List<BillDtoRes> billDtoResList = objectMapper.mapAllToDTOList(list, BillDtoRes.class);
                return billDtoResList;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public byte[] getPDF(Map<String, Object> requestMap) {
        log.info("Inside getPDF");
        log.info("requestMap {}", requestMap);
        try {
            byte[] byteArray = new byte[0];
            if (!requestMap.containsKey("uuid") && validateRequestMap(requestMap)) {
                throw new RuntimeException(CafeConstants.INVALID_DATA);
            }
            String filePath = CafeConstants.STORE_LOCATION + "\\" + (String) requestMap.get("uuid") + ".pdf";
            if (CafeUtils.isFileExist(filePath)) {
                byteArray = getByteArray(filePath);
                return byteArray;
            } else {
                requestMap.put("isGenerate", false);
                generateReport(requestMap);
                byteArray = getByteArray(filePath);
                return byteArray;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }


    private byte[] getByteArray(String filePath) throws IOException {
        File file = new File(filePath);
        InputStream targetStream = new FileInputStream(file);
        byte[] byteArray = IOUtils.toByteArray(targetStream);
        targetStream.close();
        return byteArray;
    }

    @Override
    public String deleteBill(Integer id) {
        try {
            Optional<Bill> bill = billRepository.findById(id);
            if (bill.isPresent()) {
                billRepository.deleteById(id);
                return CafeConstants.BILL_DELETED_SUCCESSFULLY;
            } else
                return CafeConstants.INVALID_DATA;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public DashboardDTO getBillsCount(DashboardDTO dashboardDTO) {
        try {
            String billsCount = String.valueOf(billRepository.count());
            dashboardDTO.setBill(billsCount);
            return dashboardDTO;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
