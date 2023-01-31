package com.ims.service;
import com.ims.entity.InventoryItem;
import com.ims.entity.Order;
import com.ims.entity.Sale;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    List<Sale> getAllSales();
    List<Order> listOfOrdersByDate(LocalDateTime startTime, LocalDateTime endTime);
    List<Order> listOrders();
}