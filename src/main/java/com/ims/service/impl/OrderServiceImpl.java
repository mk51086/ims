package com.ims.service.impl;

import com.ims.entity.InventoryItem;
import com.ims.entity.Order;
import com.ims.enums.OrderStatus;
import com.ims.repository.OrderRepository;
import com.ims.service.OrderService;
import org.apache.tomcat.jni.Address;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    public OrderServiceImpl (OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @Override
    public Order addOrder(Order order) {
        order.setStatus(OrderStatus.Progress);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void changeOrderStatus(int orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
        }
        List<InventoryItem> item = order.getInventoryItems();
        if(order.getStatus() == OrderStatus.Completed){
            for(InventoryItem inventoryItem: item){

            }
        }
    }
}
