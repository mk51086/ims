package com.ims.controller;

import com.ims.dto.OrderDTO;
import com.ims.entity.Order;
import com.ims.entity.Sale;
import com.ims.service.ReportService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
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
    public List<Sale> listOfSalesByDay(){
        return reportService.listOfSalesByDay();
    }
    @GetMapping("/listOfSalesByMonth")
    public List<Sale> listOfSalesByMonth(){
        return reportService.listOfSalesByMonth();
    }
    @GetMapping("/listOfSalesByYear")
    public List<Sale> listOfSalesByYear(){
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
}