package com.ims.service.impl;

import com.ims.entity.Order;
import com.ims.entity.Sale;
import com.ims.repository.OrderRepository;
import com.ims.repository.SaleRepository;
import com.ims.service.ReportService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<Sale> listOfSalesByDay() {
        return saleRepository.findByDay();
    }

    @Override
    public List<Sale> listOfSalesByMonth() {
        return saleRepository.findByMonth();
    }

    @Override
    public List<Sale> listOfSalesByYear() {
        return saleRepository.findByYear();
    }

    @Override
    public List<Order> listOfOrdersByDate(LocalDateTime startTime, LocalDateTime endTime) {
       List<Order> result = new ArrayList<>();
       List<Order> orders = orderRepository.findAll();
       for (Order order: orders){
          // Hibernate.initialize(order.getInventoryItems());
            if(order.getDate().isAfter(startTime) && order.getDate().isBefore(endTime)){
                result.add(order);
            }
       }
       return result;
    }
    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }
}
