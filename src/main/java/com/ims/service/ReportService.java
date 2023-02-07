package com.ims.service;
import com.ims.dto.SaleDTO;
import com.ims.entity.Order;
import com.ims.entity.Sale;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    List<Sale> getAllSales();
    List<SaleDTO> listOfSalesByDay();
    List<SaleDTO> listOfSalesByMonth();
    List<SaleDTO> listOfSalesByYear();
    List<Order> listOfOrdersByDate(LocalDateTime startTime, LocalDateTime endTime);
    List<Order> listOrders();
    void generatePdf(List<SaleDTO> sales, String fileName);
}