package com.ims.service.impl;

import com.ims.dto.OrderDTO;
import com.ims.entity.InventoryItem;
import com.ims.entity.Order;
import com.ims.entity.Company;
import com.ims.enums.OrderStatus;
import com.ims.repository.InventoryItemRepository;
import com.ims.repository.OrderRepository;
import com.ims.repository.CompanyRepository;
import com.ims.service.OrderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final CompanyRepository companyRepository;

    public OrderServiceImpl (OrderRepository orderRepository, InventoryItemRepository inventoryItemRepository, CompanyRepository companyRepository){
        this.orderRepository = orderRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.companyRepository = companyRepository;
    }
    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {
        Order order = new Order();
        return getOrderDTO(orderDTO, order);
    }

    @Override
    public OrderDTO updateOrder(Integer id,OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElse(null);
        if(order == null){
            throw new ResourceNotFoundException("Order not found with id" + id);
        }
        return getOrderDTO(orderDTO, order);
    }

    @NotNull
    private OrderDTO getOrderDTO(OrderDTO orderDTO, Order order) {
        BeanUtils.copyProperties(orderDTO,order);
        List<InventoryItem> items = inventoryItemRepository.findAllById(orderDTO.getInventoryItems());
        order.setInventoryItems(items);
        Company supplier = companyRepository.findById(orderDTO.getSupplierId()).orElse(null);
        order.setSupplier(supplier);
        orderRepository.save(order);
        return new OrderDTO(order);
    }

    @Override
    public void deleteOrder(int id) {
        Order order = orderRepository.findById(id).orElse(null);
        if(order == null){
            throw new ResourceNotFoundException("Order not found with id"+id);
        }
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDTO> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(int id) {
       Order order = orderRepository.findById(id).orElse(null);
       if(order==null){
           throw new ResourceNotFoundException("Order not found with id"+id);
       }
       return new OrderDTO(order);
    }

    @Override
    public void changeOrderStatus(Integer orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(null);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        if (status == OrderStatus.COMPLETED) {
            List<InventoryItem> inventoryItems = order.getInventoryItems();
            for (InventoryItem item : inventoryItems) {
                item.setQuantity(item.getQuantity() + order.getQuantity());
                inventoryItemRepository.save(item);
            }
        }
        order.setStatus(status);
        orderRepository.save(order);
    }

}
