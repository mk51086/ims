package com.ims.service;

import com.ims.entity.Order;

import java.util.List;

public interface OrderService {
    Order addOrder (Order order);
    Order updateOrder (Order order);
    void deleteOrder (int id);
    List<Order> getOrders ();
    Order getOrderById(int id);
}
