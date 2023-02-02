package com.ims.service;

import com.ims.entity.Order;
import com.ims.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    Order addOrder (Order order);
    Order updateOrder (Order order);
    void deleteOrder (int id);
    List<Order> getOrders ();
    Order getOrderById(int id);
    void changeOrderStatus(int orderId, OrderStatus status);
}
