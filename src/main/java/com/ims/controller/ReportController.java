package com.ims.controller;

import com.ims.entity.Order;
import com.ims.entity.Sale;
import com.ims.service.ReportService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
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

    @GetMapping("/orders")
    public List<Order> listOrders() {
        return reportService.listOrders();
    }

    @GetMapping("/{startDate}/{endDate}")
    public List<Order> listOfOrdersByDate(@PathVariable("startDate") LocalDateTime startDate,
                                          @PathVariable("endDate") LocalDateTime endDate) {
        return reportService.listOfOrdersByDate(startDate, endDate);
    }
}