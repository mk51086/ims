package com.ims.service;

import com.ims.dto.OrderDTO;
import com.ims.entity.InventoryItem;
import com.ims.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderDTO addOrder (OrderDTO orderDTO);
    OrderDTO updateOrder (Integer id,OrderDTO orderDTO);
    void deleteOrder (int id);
    List<OrderDTO> getOrders ();
    OrderDTO getOrderById(int id);

    void changeOrderStatus(Integer orderId, OrderStatus status);
}
