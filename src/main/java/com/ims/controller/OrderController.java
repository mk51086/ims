package com.ims.controller;

import com.ims.entity.Order;
import com.ims.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    public OrderController (OrderService orderService){
        this.orderService=orderService;
    }
    @PostMapping
    public Order addOrder (@RequestBody Order order){
        return orderService.addOrder(order);
    }
    @PutMapping
    public Order updateOrder(@RequestBody Order order){
        return orderService.updateOrder(order);
    }
    @DeleteMapping("/{id}")
    public void deleteOrder (@PathVariable int id){
        orderService.deleteOrder(id);
    }
    @GetMapping
    public List<Order> getOrders(){
        return orderService.getOrders();
    }
    @GetMapping("/{id}")
    public Order getOrderById (@PathVariable int id){
        return orderService.getOrderById(id);
    }
}
