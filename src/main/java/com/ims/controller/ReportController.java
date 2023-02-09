package com.ims.controller;

import com.ims.dto.OrderDTO;
import com.ims.dto.SaleDTO;
import com.ims.entity.Order;
import com.ims.entity.Sale;
import com.ims.service.ReportService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

@RestController
@RequestMapping("/report")
@Api( tags = "Report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/sales")
    public List<Sale> getAllSales() {
        return reportService.getAllSales();
    }
    @GetMapping("/listOfSalesByDay")
    public List<SaleDTO> listOfSalesByDay(){
        return reportService.listOfSalesByDay();
    }
    @GetMapping("/listOfSalesByMonth")
    public List<SaleDTO> listOfSalesByMonth(){
        return reportService.listOfSalesByMonth();
    }
    @GetMapping("/listOfSalesByYear")
    public List<SaleDTO> listOfSalesByYear(){
        return reportService.listOfSalesByYear();
    }
    @GetMapping("/listOrders")
    public List<Order> listOrders() {
        return reportService.listOrders();
    }

    @GetMapping("/listOrdersByDate")
    public List<OrderDTO> listOfOrdersByDate(@RequestParam("startDate") String startDate,
                                          @RequestParam("endDate") String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        List<Order> orders = reportService.listOfOrdersByDate(start, end);
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(new OrderDTO(order));
        }
        return orderDTOs;
    }

    @GetMapping("/pdf/listOfSalesByDay")
    public void pdfListOfSalesByDay(HttpServletResponse response){
        List<SaleDTO> sales = reportService.listOfSalesByDay();
        reportService.generatePdf(sales,"sales_by_day.pdf");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=sales_by_day.pdf");

        try {
            InputStream is = new FileInputStream("sales_by_day.pdf");
            response.setContentLength(is.available());
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/pdf/listOfSalesByMonth")
    public void pdfListOfSalesByMonth(HttpServletResponse response){
        List<SaleDTO> sales = reportService.listOfSalesByMonth();
        reportService.generatePdf(sales,"sales_by_month.pdf");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=sales_by_month.pdf");

        try {
            InputStream is = new FileInputStream("sales_by_month.pdf");
            response.setContentLength(is.available());
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/pdf/listOfSalesByYear")
    public void pdfListOfSalesByYear(HttpServletResponse response){
        List<SaleDTO> sales = reportService.listOfSalesByYear();
        reportService.generatePdf(sales,"sales_by_year.pdf");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=sales_by_year.pdf");

        try {
            InputStream is = new FileInputStream("sales_by_month.pdf");
            response.setContentLength(is.available());
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}