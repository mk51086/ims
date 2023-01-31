package com.ims.service.impl;

import com.ims.entity.Order;
import com.ims.entity.Sale;
import com.ims.repository.OrderRepository;
import com.ims.repository.SaleRepository;
import com.ims.service.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final SaleRepository saleRepository;
    private final OrderRepository orderRepository;

    public ReportServiceImpl(SaleRepository saleRepository, OrderRepository orderRepository) {
        this.saleRepository = saleRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Sale> getAllSales() {
        // TODO: further implementation of this method
        return saleRepository.findAll();
    }

    @Override
    public List<Order> listOfOrdersByDate(LocalDateTime startTime, LocalDateTime endTime) {
        //TODO: implementation of this method where we return orders between a date range
        return null;
    }

    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }
}
